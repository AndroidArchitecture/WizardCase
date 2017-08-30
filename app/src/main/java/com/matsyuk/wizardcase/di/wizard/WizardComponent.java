package com.matsyuk.wizardcase.di.wizard;

import com.matsyuk.wizardcase.presentation.activation.views.ActivationFragment;
import com.matsyuk.wizardcase.presentation.info.views.InfoFragment;
import com.matsyuk.wizardcase.presentation.license.views.LicenseFragment;
import com.matsyuk.wizardcase.presentation.main.MainActivity;

import dagger.Subcomponent;

/**
 * @author e.matsyuk
 */
@WizardScope
@Subcomponent(modules = {WizardModule.class, WizardNavigationModule.class})
public interface WizardComponent {

    @Subcomponent.Builder
    interface Builder {
        WizardComponent build();
    }

    void inject(MainActivity mainActivity);
    void inject(InfoFragment infoFragment);
    void inject(LicenseFragment licenseFragment);
    void inject(ActivationFragment activationFragment);

}
