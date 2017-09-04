package com.matsyuk.wizardcase.wizards;

import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;
import com.matsyuk.wizardcase.presentation.license.wizard_part.LicenseWizardPart;

import ru.terrakok.cicerone.Router;

import static com.matsyuk.wizardcase.wizards.WizardStep.*;
import static com.matsyuk.wizardcase.wizards.RouterConstants.*;

/**
 * @author e.matsyuk
 */
public class WizardSmartRouter {

    private final Router router;
    private WizardStep currentWizardStep = NONE;

    private final InfoWizardPart infoStartWizardPart = new InfoWizardPart() {

        @Override
        public void infoWizardNext() {
            currentWizardStep = LICENSE;
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
            currentWizardStep = ACTIVATION;
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
        public void activationWizardFreeNext() {
            currentWizardStep = FINISH_INFO;
            router.navigateTo(INFO_FINISH_SCREEN);
        }

        @Override
        public void activationLoginWizardSuccess() {
            currentWizardStep = FINISH_INFO;
            router.navigateTo(INFO_FINISH_SCREEN);
        }

        @Override
        public void activationWizardBack() {
            currentWizardStep = WizardStep.LICENSE;
            router.navigateTo(INFO_FINISH_SCREEN);
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
        if (currentWizardStep != NONE) {
            return;
        }
        currentWizardStep = START_INFO;
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

}
