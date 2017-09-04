package com.matsyuk.wizardcase.presentation.activation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.matsyuk.wizardcase.business.auth.AuthInteractor;
import com.matsyuk.wizardcase.presentation.activation.views.ActivationView;
import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author e.matsyuk
 */
@InjectViewState
public class ActivationPresenter extends MvpPresenter<ActivationView> {

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
