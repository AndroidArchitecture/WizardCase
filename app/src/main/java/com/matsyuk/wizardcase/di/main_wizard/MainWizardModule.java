package com.matsyuk.wizardcase.di.main_wizard;

import com.matsyuk.wizardcase.business.license.LicenseInteractor;
import com.matsyuk.wizardcase.business.license.LicenseInteractorFake;
import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;
import com.matsyuk.wizardcase.presentation.license.wizard_part.LicenseWizardPart;
import com.matsyuk.wizardcase.wizards.main.MainWizardSmartRouter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;

import static com.matsyuk.wizardcase.di.DiConstants.INFO_FINISH_ANNOTATION;
import static com.matsyuk.wizardcase.di.DiConstants.INFO_START_ANNOTATION;

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
    @Named(INFO_START_ANNOTATION)
    InfoWizardPart provideInfoStartWizardPartStart(MainWizardSmartRouter mainWizardSmartRouter) {
        return mainWizardSmartRouter.getInfoStartWizardPart();
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

    @MainWizardScope
    @Provides
    @Named(INFO_FINISH_ANNOTATION)
    InfoWizardPart provideInfoFinishWizardPartStart(MainWizardSmartRouter mainWizardSmartRouter) {
        return mainWizardSmartRouter.getInfoFinishWizardPart();
    }

}
