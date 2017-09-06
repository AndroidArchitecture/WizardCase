package com.matsyuk.wizardcase.business.auth;

import io.reactivex.Single;

/**
 * @author e.matsyuk
 */
public interface AuthInteractor {

    Single<Boolean> login(String login, String password);

}
