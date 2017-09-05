package com.matsyuk.wizardcase.presentation.info.views;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.matsyuk.wizardcase.di.ComponentManager;
import com.matsyuk.wizardcase.presentation.info.presenters.InfoPresenter;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;

import javax.inject.Inject;
import javax.inject.Named;

import static com.matsyuk.wizardcase.di.DiConstants.INFO_FINISH_ANNOTATION;

/**
 * @author e.matsyuk
 */
public class InfoFinishFragment extends InfoFragment {

    @Inject
    @Named(INFO_FINISH_ANNOTATION)
    InfoWizardPart infoWizardPart;

    @ProvidePresenter
    InfoPresenter provideInfoPresenter() {
        return new InfoPresenter(infoWizardPart, TextType.FINISH);
    }

    @InjectPresenter
    InfoPresenter infoPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getWizardComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected InfoPresenter getPresenter() {
        return infoPresenter;
    }

}
