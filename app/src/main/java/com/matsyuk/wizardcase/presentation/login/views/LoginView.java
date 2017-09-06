package com.matsyuk.wizardcase.presentation.login.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * @author e.matsyuk
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface LoginView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSuccessLogin();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorLogin();
    void showProgress();
    void hideProgress();
    void loginEnabled(boolean enable);
}
