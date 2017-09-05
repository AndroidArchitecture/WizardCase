package com.matsyuk.wizardcase.presentation.account;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.matsyuk.wizardcase.R;
import com.matsyuk.wizardcase.common.ui.BackButtonListener;
import com.matsyuk.wizardcase.di.ComponentManager;
import com.matsyuk.wizardcase.presentation.info.views.InfoAccountFragment;
import com.matsyuk.wizardcase.presentation.login.views.LoginFragment;
import com.matsyuk.wizardcase.presentation.registration.views.RegistrationFragment;
import com.matsyuk.wizardcase.wizards.activation.AccountWizardSmartRouter;

import javax.inject.Inject;
import javax.inject.Named;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

import static com.matsyuk.wizardcase.wizards.RouterConstants.*;

/**
 * @author e.matsyuk
 */
public class AccountActivity extends AppCompatActivity {

    @Inject
    @Named(LOGIN_NAMED_ANNOTATION)
    NavigatorHolder navigatorHolder;

    @Inject
    AccountWizardSmartRouter accountWizardSmartRouter;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.start_container) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            if (screenKey.equals(INFO_ACCOUNT_SCREEN)) {
                return new InfoAccountFragment();
            } else if (screenKey.equals(LOGIN_SCREEN)) {
                return new LoginFragment();
            } else if (screenKey.equals(REGISTRATION_SCREEN)) {
                return new RegistrationFragment();
            }
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            // no actions yet
        }

        @Override
        protected void exit() {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ComponentManager.getInstance().getFirstComponent().inject(this);
        setContentView(R.layout.ac_account);
        setTitle(getString(R.string.ac_account_wizard_title));
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        accountWizardSmartRouter.startWizard();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.start_container);
        if (fragment != null
                && fragment instanceof BackButtonListener
                && ((BackButtonListener) fragment).onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
    }

    @Override
    public void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

}
