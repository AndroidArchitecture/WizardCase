package com.matsyuk.wizardcase.presentation.activation.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * @author e.matsyuk
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface ActivationView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSuccessLogin();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorLogin();
    @StateStrategyType(OneExecutionStateStrategy.class)
    void showFreeLogin();
    void showProgress(boolean show);
    void loginEnabled(boolean enable);

}
