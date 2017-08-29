package com.matsyuk.wizardcase.presentation.info.views;

import com.arellomobile.mvp.MvpView;

/**
 * @author e.matsyuk
 */
public interface InfoView extends MvpView {
    void showText(TextType textType);
}
