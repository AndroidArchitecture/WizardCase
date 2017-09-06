package com.matsyuk.wizardcase.di.main_wizard;

import com.matsyuk.wizardcase.business.first_wizard.FirstWizardInteractor;
import com.matsyuk.wizardcase.business.first_wizard.FirstWizardInteractorFake;
import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;
import com.matsyuk.wizardcase.presentation.license.wizard_part.LicenseWizardPart;
import com.matsyuk.wizardcase.wizards.account.AccountWizardResult;
import com.matsyuk.wizardcase.wizards.main.MainWizardSmartRouter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;

import static com.matsyuk.wizardcase.di.DiConstants.*;

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
    MainWizardSmartRouter provideStartWizardManager(@Named(MAIN_WIZARD_ANNOTATION) Router router) {
        return new MainWizardSmartRouter(router);
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
    @Named(INFO_START_ANNOTATION)
    InfoWizardPart provideInfoStartWizardPartStart(MainWizardSmartRouter mainWizardSmartRouter) {
        return mainWizardSmartRouter.getInfoStartWizardPart();
    }

    @WizardScope
    @Provides
    LicenseWizardPart provideLicenseWizardPart(MainWizardSmartRouter mainWizardSmartRouter) {
        return mainWizardSmartRouter.getLicenseWizardPart();
    }

    @WizardScope
    @Provides
    ActivationWizardPart provideActivationWizardPart(MainWizardSmartRouter mainWizardSmartRouter) {
        return mainWizardSmartRouter.getActivationWizardPart();
    }

    @WizardScope
    @Provides
    @Named(INFO_FINISH_ANNOTATION)
    InfoWizardPart provideInfoFinishWizardPartStart(MainWizardSmartRouter mainWizardSmartRouter) {
        return mainWizardSmartRouter.getInfoFinishWizardPart();
    }

    @WizardScope
    @Provides
    AccountWizardResult provideAccountWizardResult(MainWizardSmartRouter mainWizardSmartRouter) {
        return mainWizardSmartRouter.getAccountWizardResult();
    }

}
