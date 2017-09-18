package com.matsyuk.wizardcase.di.main_wizard;

import com.matsyuk.wizardcase.presentation.activation.views.ActivationFragment;
import com.matsyuk.wizardcase.presentation.info.views.InfoFragment;
import com.matsyuk.wizardcase.presentation.license.views.LicenseFragment;
import com.matsyuk.wizardcase.presentation.main.MainActivity;

import dagger.Subcomponent;

/**
 * @author e.matsyuk
 */
@MainWizardScope
@Subcomponent(modules = {MainWizardModule.class, MainWizardNavigationModule.class})
public interface MainWizardComponent {

    @Subcomponent.Builder
    interface Builder {
        MainWizardComponent build();
    }

    void inject(MainActivity mainActivity);
    void inject(InfoFragment infoFragment);
    void inject(LicenseFragment licenseFragment);
    void inject(ActivationFragment activationFragment);

}
