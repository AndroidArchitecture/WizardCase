package com.matsyuk.wizardcase.presentation.info.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.matsyuk.wizardcase.R;
import com.matsyuk.wizardcase.common.ui.BackButtonListener;
import com.matsyuk.wizardcase.di.ComponentManager;
import com.matsyuk.wizardcase.presentation.info.presenters.InfoPresenter;
import com.matsyuk.wizardcase.presentation.info.wizard_part.InfoWizardPart;

import javax.inject.Inject;

/**
 * @author e.matsyuk
 */
public class InfoFragment extends MvpAppCompatFragment implements InfoView, BackButtonListener {

    @Inject
    InfoWizardPart infoWizardPart;

    @ProvidePresenter
    InfoPresenter provideInfoPresenter() {
        return new InfoPresenter(infoWizardPart, TextType.START);
    }

    @InjectPresenter
    InfoPresenter infoPresenter;

    private TextView infoText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getMainComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_info, container, false);
        //
        infoText = (TextView)view.findViewById(R.id.info_text);
        Button nextButton = (Button) view.findViewById(R.id.btn_next);
        nextButton.setOnClickListener(v -> infoPresenter.clickNext());
        //
        return view;
    }

    @Override
    public void showText(TextType textType) {
        if (textType == TextType.START) {
            infoText.setText(getString(R.string.fmt_info_text_start));
        } else if (textType == TextType.FINISH) {
            infoText.setText(getString(R.string.fmt_info_text_finish));
        } else if (textType == TextType.LOGIN) {
            infoText.setText(getString(R.string.fmt_info_text_login));
        }

    }

    @Override
    public boolean onBackPressed() {
        infoPresenter.clickBack();
        return true;
    }

}
