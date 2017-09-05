package com.matsyuk.wizardcase.presentation.login.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.matsyuk.wizardcase.R;
import com.matsyuk.wizardcase.business.auth.AuthInteractor;
import com.matsyuk.wizardcase.common.ui.BackButtonListener;
import com.matsyuk.wizardcase.di.ComponentManager;
import com.matsyuk.wizardcase.presentation.login.presenters.LoginPresenter;
import com.matsyuk.wizardcase.presentation.login.wizard_part.LoginWizardPart;

import javax.inject.Inject;

/**
 * @author e.matsyuk
 */
public class LoginFragment extends MvpAppCompatFragment implements LoginView, BackButtonListener {

    @Inject
    LoginWizardPart loginWizardPart;

    @Inject
    AuthInteractor authInteractor;

    @ProvidePresenter
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter(loginWizardPart, authInteractor);
    }

    @InjectPresenter
    LoginPresenter loginPresenter;

    private Button loginButton;
    private EditText loginInput;
    private EditText passwordInput;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ComponentManager.getInstance().getAccountWizardComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fmt_login, container, false);

        progressBar = (ProgressBar)view.findViewById(R.id.progress);

        loginInput = (EditText)view.findViewById(R.id.et_mail);
        passwordInput = (EditText)view.findViewById(R.id.et_password);

        loginButton = (Button)view.findViewById(R.id.btn_login);
        loginButton.setOnClickListener(v -> loginPresenter.clickLogin(loginInput.getText().toString(), passwordInput.getText().toString()));

        Button newAccountButton = (Button)view.findViewById(R.id.btn_reg);
        newAccountButton.setOnClickListener(v -> loginPresenter.clickNewAccount());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loginPresenter.inputData(
                RxTextView.textChanges(loginInput).map(CharSequence::toString),
                RxTextView.textChanges(passwordInput).map(CharSequence::toString)
        );
    }

    @Override
    public boolean onBackPressed() {
        loginPresenter.clickBack();
        return true;
    }

    @Override
    public void showSuccessLogin() {
        Toast.makeText(getContext(), getString(R.string.fmt_account_login_auth_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorLogin() {
        Toast.makeText(getContext(), getString(R.string.fmt_account_login_auth_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void loginEnabled(boolean enable) {
        loginButton.setEnabled(enable);
    }

}
