package com.matsyuk.wizardcase.business.first_wizard;

import io.reactivex.Single;

/**
 * @author e.matsyuk
 */
public interface MainWizardInteractor {
    Single<Boolean> acceptLicense();
}
