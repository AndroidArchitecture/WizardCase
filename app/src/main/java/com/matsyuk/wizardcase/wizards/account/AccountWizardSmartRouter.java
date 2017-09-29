package com.matsyuk.wizardcase.wizards.account;

import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;
import com.matsyuk.wizardcase.presentation.login.wizard_part.LoginWizardPart;
import com.matsyuk.wizardcase.presentation.registration.wizard_part.RegistrationWizardPart;

import ru.terrakok.cicerone.Router;

import static com.matsyuk.wizardcase.wizards.RouterConstants.*;
import static com.matsyuk.wizardcase.wizards.account.AccountWizardStep.*;

/**
 * @author e.matsyuk
 */
public class AccountWizardSmartRouter {

    private final Router router;
    private final AccountWizardPart accountWizardPart;
    private AccountWizardStep accountWizardStep = NONE;

    private final InfoWizardPart infoWizardPart = new InfoWizardPart() {

        @Override
        public void infoWizardNext() {
            accountWizardStep = LOGIN;
            router.navigateTo(LOGIN_SCREEN);
        }

        @Override
        public void infoWizardBack() {
            router.finishChain();
        }

    };

    private final LoginWizardPart loginWizardPart = new LoginWizardPart() {

        @Override
        public void loginWizardSuccess() {
            router.finishChain();
            accountWizardPart.onSuccess();
        }

        @Override
        public void loginWizardBack() {
            router.finishChain();
            accountWizardPart.onBack();
        }

        @Override
        public void loginWizardNewAccount() {
            accountWizardStep = REGISTRATION;
            router.navigateTo(REGISTRATION_SCREEN);
        }

    };

    private final RegistrationWizardPart registrationWizardPart = new RegistrationWizardPart() {

        @Override
        public void registrationWizardSuccess() {
            router.finishChain();
            accountWizardPart.onSuccess();
        }

        @Override
        public void registrationWizardBack() {
            accountWizardStep = LOGIN;
            router.backTo(LOGIN_SCREEN);
        }

    };

    public AccountWizardSmartRouter(Router router,
                                    AccountWizardPart accountWizardPart) {
        this.router = router;
        this.accountWizardPart = accountWizardPart;
    }

    public void startWizard() {
        if (accountWizardStep != NONE) {
            return;
        }
        accountWizardStep = INFO;
        router.navigateTo(INFO_ACCOUNT_SCREEN);
    }

    public InfoWizardPart getInfoWizardPart() {
        return infoWizardPart;
    }

    public LoginWizardPart getLoginWizardPart() {
        return loginWizardPart;
    }

    public RegistrationWizardPart getRegistrationWizardPart() {
        return registrationWizardPart;
    }

}
