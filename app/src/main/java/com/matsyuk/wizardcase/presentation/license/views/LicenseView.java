package com.matsyuk.wizardcase.presentation.license.views;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * @author e.matsyuk
 */
@StateStrategyType(AddToEndSingleStrategy.class)
public interface LicenseView extends MvpView {

    void showProgress();
    void hideProgress();

}
