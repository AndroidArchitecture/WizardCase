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
public class WizardSmartRouter implements InfoWizardPart, LicenseWizardPart, ActivationWizardPart {

    private final Router router;
    private WizardStep currentWizardStep = NONE;

    public WizardSmartRouter(Router router) {
        this.router = router;
    }

    public void startWizard() {
        if (currentWizardStep != NONE) {
            return;
        }
        currentWizardStep = START_INFO;
        router.navigateTo(INFO_SCREEN);
    }

    /**
     * InfoWizardPart
     */

    @Override
    public void infoWizardNext() {
        if (currentWizardStep == START_INFO) {
            currentWizardStep = LICENSE;
            router.navigateTo(LICENSE_SCREEN);
        } else if (currentWizardStep == FINISH_INFO) {
            router.finishChain();
        }
    }

    @Override
    public void infoWizardBack() {
        router.finishChain();
    }

    /**
     * LicenseWizardPart
     */

    @Override
    public void licenseWizardAccept() {
        currentWizardStep = ACTIVATION;
        router.navigateTo(ACTIVATION_SCREEN);
    }

    @Override
    public void licenseWizardBack() {
        currentWizardStep = WizardStep.START_INFO;
        router.backTo(INFO_SCREEN);
    }

    /**
     * ActivationWizardPart
     */

    @Override
    public void activationWizardFreeNext() {
        currentWizardStep = FINISH_INFO;
        router.finishChain();
    }

    @Override
    public void activationLoginWizardSuccess() {

    }

    @Override
    public void activationWizardBack() {
        currentWizardStep = WizardStep.LICENSE;
        router.backTo(LICENSE_SCREEN);
    }

}
