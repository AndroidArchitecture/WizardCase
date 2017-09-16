package com.matsyuk.wizardcase.business.license;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

/**
 * @author e.matsyuk
 */
public class LicenseInteractorFake implements LicenseInteractor {

    @Override
    public Single<Boolean> acceptLicense() {
        return Single.timer(2, TimeUnit.SECONDS)
                .map(aLong -> true);
    }

}
