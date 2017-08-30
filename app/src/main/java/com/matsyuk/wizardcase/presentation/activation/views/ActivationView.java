package com.matsyuk.wizardcase.presentation.activation.views;

import com.arellomobile.mvp.MvpView;

/**
 * @author e.matsyuk
 */
public interface ActivationView extends MvpView {

    void showSuccessLogin();
    void showErrorLogin();
    void showProgress(boolean show);
    void loginEnabled(boolean enable);

}
