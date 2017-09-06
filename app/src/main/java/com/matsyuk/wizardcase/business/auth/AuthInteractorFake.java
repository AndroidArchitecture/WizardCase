package com.matsyuk.wizardcase.business.auth;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;

/**
 * @author e.matsyuk
 */
public class AuthInteractorFake implements AuthInteractor {

    private static final String TEST = "test";

    @Override
    public Single<Boolean> login(String login, String password) {
        return Single.timer(2, TimeUnit.SECONDS)
                .map(aLong -> login.equals(TEST) && password.equals(TEST));
    }

}
