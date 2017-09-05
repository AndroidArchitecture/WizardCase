package com.matsyuk.wizardcase.presentation.registration.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.matsyuk.wizardcase.R;
import com.matsyuk.wizardcase.business.auth.AuthInteractor;
import com.matsyuk.wizardcase.common.ui.BackButtonListener;
import com.matsyuk.wizardcase.di.ComponentManager;
import com.matsyuk.wizardcase.presentation.registration.presenters.RegistrationPresenter;
import com.matsyuk.wizardcase.presentation.registration.wizard_part.RegistrationWizardPart;

import javax.inject.Inject;

/**
 * @author e.matsyuk
 */
public class RegistrationFragment extends MvpAppCompatFragment implements RegistrationView, BackButtonListener {

    @Inject
    RegistrationWizardPart registrationWizardPart;

    @Inject
    AuthInteractor authInteractor;

    @ProvidePresenter
    RegistrationPresenter provideRegistrationPresenter() {
        return new RegistrationPresenter(registrationWizardPart, authInteractor);
    }

    @InjectPresenter
    RegistrationPresenter registrationPresenter;

    private EditText mailInput;
    private EditText passwordInput;
    private EditText retryPasswordInput;
    private ProgressBar progressBar;
    private Button registrationButton;

    private TextWatcher commonTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            registrationPresenter.inputText();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getAccountWizardComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_registration, container, false);

        progressBar = (ProgressBar)view.findViewById(R.id.progress);

        mailInput = (EditText)view.findViewById(R.id.et_mail);
        mailInput.addTextChangedListener(commonTextWatcher);
        passwordInput = (EditText)view.findViewById(R.id.et_password);
        passwordInput.addTextChangedListener(commonTextWatcher);
        retryPasswordInput = (EditText)view.findViewById(R.id.et_password_retry);
        retryPasswordInput.addTextChangedListener(commonTextWatcher);

        registrationButton = (Button)view.findViewById(R.id.btn_registration);
        registrationButton.setOnClickListener(v -> registrationPresenter.clickRegistration(
                mailInput.getText().toString(),
                passwordInput.getText().toString(),
                retryPasswordInput.getText().toString()
        ));

        return view;
    }

    @Override
    public boolean onBackPressed() {
        registrationPresenter.clickBack();
        return true;
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMail() {
        mailInput.setError(getString(R.string.fmt_account_registration_et_mail_error));
    }

    @Override
    public void showPasswordError() {
        passwordInput.setError(getString(R.string.fmt_account_registration_et_password_error));
    }

    @Override
    public void showAuthError() {
        Toast.makeText(getContext(), getString(R.string.fmt_account_registration_auth_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess() {
        Toast.makeText(getContext(), getString(R.string.fmt_account_registration_auth_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clearInputErrors() {
        mailInput.setError(null);
        passwordInput.setError(null);
        retryPasswordInput.setError(null);
    }

}
