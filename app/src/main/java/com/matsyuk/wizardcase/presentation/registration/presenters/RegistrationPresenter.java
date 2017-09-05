package com.matsyuk.wizardcase.presentation.registration.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.matsyuk.wizardcase.business.auth.AuthInteractor;
import com.matsyuk.wizardcase.presentation.registration.views.RegistrationView;
import com.matsyuk.wizardcase.presentation.registration.wizard_part.RegistrationWizardPart;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author e.matsyuk
 */
@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView> {

    private RegistrationWizardPart registrationWizardPart;
    private AuthInteractor authInteractor;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public RegistrationPresenter(RegistrationWizardPart registrationWizardPart,
                                 AuthInteractor authInteractor) {
        this.registrationWizardPart = registrationWizardPart;
        this.authInteractor = authInteractor;
    }

    public void clickRegistration(String mail, String password, String retryPassword) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            getViewState().showErrorMail();
            return;
        }
        if (!password.equals(retryPassword)) {
            getViewState().showPasswordError();
            return;
        }
        Disposable disposable = authInteractor.registration(mail, password)
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleRegistrationResult, throwable -> {});
        compositeDisposable.add(disposable);
    }

    public void inputText() {
        getViewState().clearInputErrors();
    }

    private void handleRegistrationResult(boolean success) {
        if (success) {
            getViewState().showSuccess();
            registrationWizardPart.accountRegistrationWizardSuccess();
        } else {
            getViewState().showAuthError();
        }
    }

    public void clickBack() {
        registrationWizardPart.accountRegistrationWizardBack();
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}
