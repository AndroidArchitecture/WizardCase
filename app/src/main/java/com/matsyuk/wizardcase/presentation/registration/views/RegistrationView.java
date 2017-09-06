package com.matsyuk.wizardcase.presentation.registration.views;

import com.arellomobile.mvp.MvpView;

/**
 * @author e.matsyuk
 */
public interface RegistrationView extends MvpView {

    void showProgress();
    void hideProgress();
    void showErrorMail();
    void showPasswordError();
    void showAuthError();
    void showSuccess();
    void clearInputErrors();

}
