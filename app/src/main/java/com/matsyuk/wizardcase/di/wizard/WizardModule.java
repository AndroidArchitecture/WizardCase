package com.matsyuk.wizardcase.di.wizard;

import com.matsyuk.wizardcase.business.main_wizard.MainWizardInteractor;
import com.matsyuk.wizardcase.business.main_wizard.MainWizardInteractorFake;
import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;
import com.matsyuk.wizardcase.presentation.license.wizard_part.LicenseWizardPart;
import com.matsyuk.wizardcase.wizards.WizardSmartRouter;

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
    MainWizardInteractor provideFirstWizardInteractor() {
        return new MainWizardInteractorFake();
    }

    /**
     * Wizard parts
     */

    @WizardScope
    @Provides
    InfoWizardPart provideInfoWizardPartStart(WizardSmartRouter wizardSmartRouter) {
        return wizardSmartRouter.getInfoWizardPart();
    }

    @WizardScope
    @Provides
    LicenseWizardPart provideLicenseWizardPart(WizardSmartRouter wizardSmartRouter) {
        return wizardSmartRouter.getLicenseWizardPart();
    }

    @WizardScope
    @Provides
    ActivationWizardPart provideActivationWizardPart(WizardSmartRouter wizardSmartRouter) {
        return wizardSmartRouter.getActivationWizardPart();
    }

}
