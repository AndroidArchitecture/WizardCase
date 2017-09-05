package com.matsyuk.wizardcase.presentation.login.views;

import com.arellomobile.mvp.MvpView;

/**
 * @author e.matsyuk
 */
public interface LoginView extends MvpView {

    void showSuccessLogin();
    void showErrorLogin();
    void showProgress(boolean show);
    void loginEnabled(boolean enable);
}
