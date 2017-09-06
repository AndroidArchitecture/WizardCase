package com.matsyuk.wizardcase.wizards.main;

import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;
import com.matsyuk.wizardcase.presentation.license.wizard_part.LicenseWizardPart;
import com.matsyuk.wizardcase.wizards.activation.AccountWizardResult;

import ru.terrakok.cicerone.Router;

import static com.matsyuk.wizardcase.wizards.RouterConstants.*;

/**
 * @author e.matsyuk
 */
public class WizardSmartRouter {

    private final Router router;
    private WizardStep currentWizardStep = WizardStep.NONE;

    private final InfoWizardPart infoStartWizardPart = new InfoWizardPart() {

        @Override
        public void infoWizardNext() {
            currentWizardStep = WizardStep.LICENSE;
            router.navigateTo(LICENSE_SCREEN);
        }

        @Override
        public void infoWizardBack() {
            router.finishChain();
        }

    };

    private final LicenseWizardPart licenseWizardPart = new LicenseWizardPart() {

        @Override
        public void licenseWizardAccept() {
            currentWizardStep = WizardStep.ACTIVATION;
            router.navigateTo(ACTIVATION_SCREEN);
        }

        @Override
        public void licenseWizardBack() {
            currentWizardStep = WizardStep.START_INFO;
            router.backTo(INFO_START_SCREEN);
        }

    };

    private final ActivationWizardPart activationWizardPart = new ActivationWizardPart() {

        @Override
        public void activationWizardPersonalAccountNext() {
            router.navigateTo(ACCOUNT_SCREEN);
        }

        @Override
        public void activationWizardFreeNext() {
            currentWizardStep = WizardStep.FINISH_INFO;
            router.navigateTo(INFO_FINISH_SCREEN);
        }

        @Override
        public void activationWizardBack() {
            currentWizardStep = WizardStep.LICENSE;
            router.backTo(LICENSE_SCREEN);
        }

    };

    private final AccountWizardResult accountWizardResult = new AccountWizardResult() {

        @Override
        public void onSuccess() {
            currentWizardStep = WizardStep.FINISH_INFO;
            router.navigateTo(INFO_FINISH_SCREEN);
        }

        @Override
        public void onBack() {
            currentWizardStep = WizardStep.ACTIVATION;
            router.backTo(ACTIVATION_SCREEN);
        }

    };

    private final InfoWizardPart infoFinishWizardPart = new InfoWizardPart() {

        @Override
        public void infoWizardNext() {
            router.finishChain();
        }

        @Override
        public void infoWizardBack() {
            router.finishChain();
        }

    };

    public WizardSmartRouter(Router router) {
        this.router = router;
    }

    public void startWizard() {
        if (currentWizardStep != WizardStep.NONE) {
            return;
        }
        currentWizardStep = WizardStep.START_INFO;
        router.navigateTo(INFO_START_SCREEN);
    }

    public InfoWizardPart getInfoStartWizardPart() {
        return infoStartWizardPart;
    }

    public LicenseWizardPart getLicenseWizardPart() {
        return licenseWizardPart;
    }

    public ActivationWizardPart getActivationWizardPart() {
        return activationWizardPart;
    }

    public InfoWizardPart getInfoFinishWizardPart() {
        return infoFinishWizardPart;
    }

    public AccountWizardResult getAccountWizardResult() {
        return accountWizardResult;
    }

}
