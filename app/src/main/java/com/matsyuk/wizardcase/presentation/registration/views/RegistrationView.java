package com.matsyuk.wizardcase.presentation.registration.views;

import com.arellomobile.mvp.MvpView;

/**
 * @author e.matsyuk
 */
public interface RegistrationView extends MvpView {

    void showProgress(boolean show);
    void showErrorMail();
    void showPasswordError();
    void showAuthError();
    void showSuccess();
    void clearInputErrors();

}
