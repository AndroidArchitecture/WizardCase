package com.matsyuk.wizardcase.di.main_wizard;

import com.matsyuk.wizardcase.business.license.LicenseInteractor;
import com.matsyuk.wizardcase.business.license.LicenseInteractorFake;
import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;
import com.matsyuk.wizardcase.presentation.license.wizard_part.LicenseWizardPart;
import com.matsyuk.wizardcase.wizards.main.MainWizardSmartRouter;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;

/**
 * @author e.matsyuk
 */
@Module
public class MainWizardModule {

    /**
     * Smart router
     */

    @MainWizardScope
    @Provides
    MainWizardSmartRouter provideStartWizardManager(Router router) {
        return new MainWizardSmartRouter(router);
    }

    /**
     * Interactors
     */

    @MainWizardScope
    @Provides
    LicenseInteractor provideFirstWizardInteractor() {
        return new LicenseInteractorFake();
    }

    /**
     * Wizard parts
     */

    @MainWizardScope
    @Provides
    InfoWizardPart provideInfoWizardPartStart(MainWizardSmartRouter mainWizardSmartRouter) {
        return mainWizardSmartRouter.getInfoWizardPart();
    }

    @MainWizardScope
    @Provides
    LicenseWizardPart provideLicenseWizardPart(MainWizardSmartRouter mainWizardSmartRouter) {
        return mainWizardSmartRouter.getLicenseWizardPart();
    }

    @MainWizardScope
    @Provides
    ActivationWizardPart provideActivationWizardPart(MainWizardSmartRouter mainWizardSmartRouter) {
        return mainWizardSmartRouter.getActivationWizardPart();
    }

}
