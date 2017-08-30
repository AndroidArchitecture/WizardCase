package com.matsyuk.wizardcase.presentation.activation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.matsyuk.wizardcase.presentation.activation.views.IActivationView;
import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;

/**
 * @author e.matsyuk
 */
@InjectViewState
public class ActivationPresenter extends MvpPresenter<IActivationView> {

    private ActivationWizardPart activationWizardPart;

    public ActivationPresenter(ActivationWizardPart activationWizardPart) {
        this.activationWizardPart = activationWizardPart;
    }

    public void clickPersonalAccount() {
        activationWizardPart.activationWizardPersonalAccountNext();
    }

    public void clickFreeVersion() {
        activationWizardPart.activationWizardFreeNext();
    }

    public void clickBack() {
        activationWizardPart.activationWizardBack();
    }

}
