package com.matsyuk.wizardcase.presentation.login.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.matsyuk.wizardcase.business.auth.AuthInteractor;
import com.matsyuk.wizardcase.presentation.login.views.LoginView;
import com.matsyuk.wizardcase.presentation.login.wizard_part.LoginWizardPart;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author e.matsyuk
 */
@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    private LoginWizardPart loginWizardPart;
    private AuthInteractor authInteractor;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LoginPresenter(LoginWizardPart loginWizardPart,
                          AuthInteractor authInteractor) {
        this.loginWizardPart = loginWizardPart;
        this.authInteractor = authInteractor;
    }

    public void clickLogin(String login, String password) {
        Disposable disposable = authInteractor.login(login, password)
                .doOnSubscribe(disposable1 -> getViewState().showProgress(true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, throwable -> {});
        compositeDisposable.add(disposable);
    }

    private void handleResult(boolean success) {
        getViewState().showProgress(false);
        if (success) {
            getViewState().showSuccessLogin();
            loginWizardPart.accountLoginWizardSuccess();
        } else {
            getViewState().showErrorLogin();
        }
    }

    public void clickNewAccount() {
        loginWizardPart.accountLoginWizardNewAccount();
    }

    public void clickBack() {
        loginWizardPart.accountLoginWizardBack();
    }

    public void inputData(Observable<String> loginObservable, Observable<String> passwordObservable) {
        Disposable disposable = Observable.combineLatest(loginObservable, passwordObservable,
                (login, password) -> !login.isEmpty() && !password.isEmpty())
                .subscribe(getViewState()::loginEnabled, throwable -> {});
        compositeDisposable.add(disposable);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}
