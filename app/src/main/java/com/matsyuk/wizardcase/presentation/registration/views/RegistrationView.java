package com.matsyuk.wizardcase.presentation.registration.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * @author e.matsyuk
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface RegistrationView extends MvpView {

    void showProgress();
    void hideProgress();
    void showErrorMail();
    void showPasswordError();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showAuthError();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSuccess();
    void clearInputErrors();

}
