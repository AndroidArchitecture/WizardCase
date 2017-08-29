package com.matsyuk.wizardcase.presentation.license.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.matsyuk.wizardcase.R;
import com.matsyuk.wizardcase.business.first_wizard.FirstWizardInteractor;
import com.matsyuk.wizardcase.common.ui.BackButtonListener;
import com.matsyuk.wizardcase.presentation.license.presenters.LicensePresenter;
import com.matsyuk.wizardcase.presentation.license.wizard_part.LicenseWizardPart;

import javax.inject.Inject;

/**
 * @author e.matsyuk
 */
public class LicenseFragment extends MvpAppCompatFragment implements LicenseView, BackButtonListener {

    @Inject
    LicenseWizardPart licenseWizardPart;

    @Inject
    FirstWizardInteractor firstWizardInteractor;

    @ProvidePresenter
    LicensePresenter provideLicensePresenter() {
        return new LicensePresenter(licenseWizardPart, firstWizardInteractor);
    }

    @InjectPresenter
    LicensePresenter licensePresenter;

    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_license, container, false);

        Button nextButton = (Button) view.findViewById(R.id.btn_next);
        nextButton.setOnClickListener(v -> licensePresenter.acceptLicense());
        progressBar = (ProgressBar) view.findViewById(R.id.progress);

        return view;
    }

    @Override
    public boolean onBackPressed() {
        licensePresenter.clickBack();
        return true;
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

}
