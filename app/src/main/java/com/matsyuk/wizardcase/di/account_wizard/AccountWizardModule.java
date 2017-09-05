package com.matsyuk.wizardcase.di.account_wizard;

import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;
import com.matsyuk.wizardcase.presentation.login.wizard_part.LoginWizardPart;
import com.matsyuk.wizardcase.presentation.registration.wizard_part.RegistrationWizardPart;
import com.matsyuk.wizardcase.wizards.activation.AccountWizardResult;
import com.matsyuk.wizardcase.wizards.activation.AccountWizardSmartRouter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Router;

import static com.matsyuk.wizardcase.di.DiConstants.*;

/**
 * @author e.matsyuk
 */
@Module
public class AccountWizardModule {

    /**
     * Smart router
     */

    @AccountWizardScope
    @Provides
    public AccountWizardSmartRouter provideAccountWizardSmartRouter(@Named(ACCOUNT_WIZARD_ANNOTATION) Router router,
                                                                    AccountWizardResult accountWizardResult) {
        return new AccountWizardSmartRouter(router, accountWizardResult);
    }

    /**
     * Wizard parts
     */

    @AccountWizardScope
    @Provides
    @Named(INFO_ACTIVATION_ANNOTATION)
    InfoWizardPart provideInfoWizardPartStart(AccountWizardSmartRouter accountWizardSmartRouter) {
        return accountWizardSmartRouter.getInfoWizardPart();
    }

    @AccountWizardScope
    @Provides
    LoginWizardPart provideLoginWizardPart(AccountWizardSmartRouter accountWizardSmartRouter) {
        return accountWizardSmartRouter.getLoginWizardPart();
    }

    @AccountWizardScope
    @Provides
    RegistrationWizardPart provideRegistrationWizardPart(AccountWizardSmartRouter accountWizardSmartRouter) {
        return accountWizardSmartRouter.getRegistrationWizardPart();
    }

}
