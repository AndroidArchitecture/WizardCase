package com.matsyuk.wizardcase.wizards.main;

import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;
import com.matsyuk.wizardcase.presentation.license.wizard_part.LicenseWizardPart;

import ru.terrakok.cicerone.Router;

import static com.matsyuk.wizardcase.wizards.RouterConstants.ACTIVATION_SCREEN;
import static com.matsyuk.wizardcase.wizards.RouterConstants.INFO_FINISH_SCREEN;
import static com.matsyuk.wizardcase.wizards.RouterConstants.INFO_START_SCREEN;
import static com.matsyuk.wizardcase.wizards.RouterConstants.LICENSE_SCREEN;

/**
 * @author e.matsyuk
 */
public class MainWizardSmartRouter {

    private final Router router;
    private MainWizardStep currentMainWizardStep = MainWizardStep.NONE;

    private final InfoWizardPart infoStartWizardPart = new InfoWizardPart() {

        @Override
        public void infoWizardNext() {
            currentMainWizardStep = MainWizardStep.LICENSE;
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
            currentMainWizardStep = MainWizardStep.ACTIVATION;
            router.navigateTo(ACTIVATION_SCREEN);
        }

        @Override
        public void licenseWizardBack() {
            currentMainWizardStep = MainWizardStep.START_INFO;
            router.backTo(INFO_START_SCREEN);
        }

    };

    private final ActivationWizardPart activationWizardPart = new ActivationWizardPart() {

        @Override
        public void activationWizardFreeNext() {
            currentMainWizardStep = MainWizardStep.FINISH_INFO;
            router.navigateTo(INFO_FINISH_SCREEN);
        }

        @Override
        public void activationLoginWizardSuccess() {
            currentMainWizardStep = MainWizardStep.FINISH_INFO;
            router.navigateTo(INFO_FINISH_SCREEN);
        }

        @Override
        public void activationWizardBack() {
            currentMainWizardStep = MainWizardStep.LICENSE;
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

    public MainWizardSmartRouter(Router router) {
        this.router = router;
    }

    public void startWizard() {
        if (currentMainWizardStep != MainWizardStep.NONE) {
            return;
        }
        currentMainWizardStep = MainWizardStep.START_INFO;
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
