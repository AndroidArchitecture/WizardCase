package com.matsyuk.wizardcase.business.license;

import io.reactivex.Single;

/**
 * @author e.matsyuk
 */
public interface LicenseInteractor {
    Single<Boolean> acceptLicense();
}
