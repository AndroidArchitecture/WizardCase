package com.matsyuk.wizardcase.presentation.activation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.matsyuk.wizardcase.business.auth.AuthInteractor;
import com.matsyuk.wizardcase.presentation.activation.views.ActivationView;
import com.matsyuk.wizardcase.presentation.activation.wizard_part.ActivationWizardPart;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author e.matsyuk
 */
@InjectViewState
public class ActivationPresenter extends MvpPresenter<ActivationView> {

    private ActivationWizardPart activationWizardPart;
    private AuthInteractor authInteractor;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public ActivationPresenter(ActivationWizardPart activationWizardPart, AuthInteractor authInteractor) {
        this.activationWizardPart = activationWizardPart;
        this.authInteractor = authInteractor;
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
    }

    public void clickLogin(String login, String password) {
        getViewState().showProgress(true);
        Disposable disposable = authInteractor.login(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResult, throwable -> {});
        compositeDisposable.add(disposable);
    }

    private void handleResult(boolean success) {
        getViewState().showProgress(false);
        if (success) {
            getViewState().showSuccessLogin();
            activationWizardPart.activationLoginWizardSuccess();
        } else {
            getViewState().showErrorLogin();
        }
    }

    public void clickBack() {
        activationWizardPart.activationWizardBack();
    }

    public void inputData(Observable<String> loginObservable, Observable<String> passwordObservable) {
        Disposable disposable = Observable.combineLatest(loginObservable, passwordObservable,
                (login, password) -> !login.isEmpty() && !password.isEmpty())
                .subscribe(getViewState()::loginEnabled, throwable -> {});
        compositeDisposable.add(disposable);
    }

    public void clickFreeVersion() {
        activationWizardPart.activationWizardFreeNext();
    }

}
