package com.matsyuk.wizardcase.presentation.info.views;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.matsyuk.wizardcase.di.ComponentManager;
import com.matsyuk.wizardcase.presentation.info.presenters.InfoPresenter;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;

import javax.inject.Inject;
import javax.inject.Named;

import static com.matsyuk.wizardcase.di.DiConstants.INFO_ACTIVATION_ANNOTATION;

/**
 * @author e.matsyuk
 */
public class InfoAccountFragment extends InfoFragment {

    @Inject
    @Named(INFO_ACTIVATION_ANNOTATION)
    InfoWizardPart infoWizardPart;

    @ProvidePresenter
    InfoPresenter provideInfoPresenter() {
        return new InfoPresenter(infoWizardPart, TextType.ACTIVATION);
    }

    @InjectPresenter
    InfoPresenter infoPresenter;

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        ComponentManager.getInstance().getMainComponent().inject(this);
//        super.onCreate(savedInstanceState);
//    }

    @Override
    protected InfoPresenter getPresenter() {
        return infoPresenter;
    }

}
