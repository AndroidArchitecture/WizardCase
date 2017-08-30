package com.matsyuk.wizardcase.presentation.activation.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.matsyuk.wizardcase.R;
import com.matsyuk.wizardcase.common.ui.BackButtonListener;
import com.matsyuk.wizardcase.presentation.activation.presenters.ActivationPresenter;
import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;

import javax.inject.Inject;

/**
 * @author e.matsyuk
 */
public abstract class ActivationFragment extends MvpAppCompatFragment implements IActivationView, BackButtonListener {

    @Inject
    ActivationWizardPart activationWizardPart;

    @ProvidePresenter
    ActivationPresenter provideActivationPresenter() {
        return new ActivationPresenter(activationWizardPart);
    }

    @InjectPresenter
    ActivationPresenter activationPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_activation, container, false);
        //
        Button personalAccountButton = (Button)view.findViewById(R.id.btn_personal_account);
        personalAccountButton.setOnClickListener(v -> activationPresenter.clickPersonalAccount());
        //
        Button freeButton = (Button)view.findViewById(R.id.btn_free);
        freeButton.setOnClickListener(v -> activationPresenter.clickFreeVersion());
        //
        return view;
    }

    @Override
    public boolean onBackPressed() {
        activationPresenter.clickBack();
        return true;
    }

}
