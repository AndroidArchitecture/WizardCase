package com.matsyuk.wizardcase.di.account_wizard;

import com.matsyuk.wizardcase.presentation.account.AccountActivity;
import com.matsyuk.wizardcase.presentation.info.views.InfoAccountFragment;
import com.matsyuk.wizardcase.presentation.login.views.LoginFragment;
import com.matsyuk.wizardcase.presentation.registration.views.RegistrationFragment;

import dagger.Subcomponent;

/**
 * @author e.matsyuk
 */
@AccountWizardScope
@Subcomponent(modules = {AccountWizardNavigationModule.class, AccountWizardModule.class})
public interface AccountWizardComponent {

    @Subcomponent.Builder
    interface Builder {
        AccountWizardComponent build();
    }

    void inject(AccountActivity accountActivity);
    void inject(RegistrationFragment registrationFragment);
    void inject(LoginFragment loginFragment);
    void inject(InfoAccountFragment infoAccountFragment);

}
