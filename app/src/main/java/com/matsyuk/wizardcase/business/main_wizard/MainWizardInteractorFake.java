package com.matsyuk.wizardcase.business.main_wizard;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

/**
 * @author e.matsyuk
 */
public class MainWizardInteractorFake implements MainWizardInteractor {

    @Override
    public Single<Boolean> acceptLicense() {
        return Single.timer(2, TimeUnit.SECONDS)
                .map(aLong -> true);
    }

}
