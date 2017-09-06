package com.matsyuk.wizardcase.presentation.login.views;

import com.arellomobile.mvp.MvpView;

/**
 * @author e.matsyuk
 */
public interface LoginView extends MvpView {

    void showSuccessLogin();
    void showErrorLogin();
    void showProgress();
    void hideProgress();
    void loginEnabled(boolean enable);
}
