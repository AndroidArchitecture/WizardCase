package com.matsyuk.wizardcase.di.main_wizard;

import com.matsyuk.wizardcase.presentation.activation.views.ActivationFragment;
import com.matsyuk.wizardcase.presentation.info.views.InfoFinishFragment;
import com.matsyuk.wizardcase.presentation.info.views.InfoStartFragment;
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
    void inject(InfoStartFragment infoStartFragment);
    void inject(InfoFinishFragment infoFinishFragment);
    void inject(LicenseFragment licenseFragment);
    void inject(ActivationFragment activationFragment);

}
