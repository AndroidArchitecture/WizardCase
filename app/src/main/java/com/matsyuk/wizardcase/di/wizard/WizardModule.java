package com.matsyuk.wizardcase.di.wizard;

import com.matsyuk.wizardcase.business.first_wizard.FirstWizardInteractor;
import com.matsyuk.wizardcase.business.first_wizard.FirstWizardInteractorFake;
import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;
import com.matsyuk.wizardcase.presentation.license.wizard_part.LicenseWizardPart;
import com.matsyuk.wizardcase.wizards.WizardSmartRouter;

import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;

/**
 * @author e.matsyuk
 */
@Module
public class WizardModule {

    /**
     * Smart router
     */

    @WizardScope
    @Provides
    WizardSmartRouter provideStartWizardManager(Router router) {
        return new WizardSmartRouter(router);
    }

    /**
     * Interactors
     */

    @WizardScope
    @Provides
    FirstWizardInteractor provideFirstWizardInteractor() {
        return new FirstWizardInteractorFake();
    }

    /**
     * Wizard parts
     */

    @WizardScope
    @Provides
    InfoWizardPart provideIInfoWizardPartStart(WizardSmartRouter wizardSmartRouter) {
        return wizardSmartRouter.getInfoWizardPart();
    }

    @WizardScope
    @Provides
    LicenseWizardPart provideILicenseWizardPart(WizardSmartRouter wizardSmartRouter) {
        return wizardSmartRouter.getLicenseWizardPart();
    }

    @WizardScope
    @Provides
    ActivationWizardPart provideIActivationWizardPart(WizardSmartRouter wizardSmartRouter) {
        return wizardSmartRouter.getActivationWizardPart();
    }

}
