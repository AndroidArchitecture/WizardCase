package com.matsyuk.wizardcase.presentation.license.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.matsyuk.wizardcase.business.first_wizard.MainWizardInteractor;
import com.matsyuk.wizardcase.presentation.license.views.LicenseView;
import com.matsyuk.wizardcase.presentation.license.wizard_part.LicenseWizardPart;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author e.matsyuk
 */
@InjectViewState
public class LicensePresenter extends MvpPresenter<LicenseView> {

    private LicenseWizardPart licenseWizardPart;
    private MainWizardInteractor mainWizardInteractor;

    private Disposable disposable;

    public LicensePresenter(LicenseWizardPart licenseWizardPart, MainWizardInteractor mainWizardInteractor) {
        this.licenseWizardPart = licenseWizardPart;
        this.mainWizardInteractor = mainWizardInteractor;
    }

    public void acceptLicense() {
        if (disposable != null && !disposable.isDisposed()) {
            return;
        }
        disposable = mainWizardInteractor.acceptLicense()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showProgress())
                .subscribe(aBoolean -> {
                    getViewState().hideProgress();
                    licenseWizardPart.licenseWizardAccept();
                }, throwable -> {});
    }

    public void clickBack() {
        licenseWizardPart.licenseWizardBack();
    }

    @Override
    public void onDestroy() {
        if (disposable != null) {
            disposable.dispose();
        }
        super.onDestroy();
    }

}
