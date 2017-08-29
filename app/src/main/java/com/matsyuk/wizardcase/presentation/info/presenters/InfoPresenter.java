package com.matsyuk.wizardcase.presentation.info.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.matsyuk.wizardcase.presentation.info.views.InfoView;
import com.matsyuk.wizardcase.presentation.info.views.TextType;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;

/**
 * @author e.matsyuk
 */
@InjectViewState
public class InfoPresenter extends MvpPresenter<InfoView> {

    private InfoWizardPart wizardPart;
    private TextType textType;

    public InfoPresenter(InfoWizardPart wizardPart, TextType textType) {
        this.wizardPart = wizardPart;
        this.textType = textType;
    }

    @Override
    protected void onFirstViewAttach() {
        getViewState().showText(textType);
    }

    public void clickNext() {
        wizardPart.infoWizardNext();
    }

    public void clickBack() {
        wizardPart.infoWizardBack();
    }

}
