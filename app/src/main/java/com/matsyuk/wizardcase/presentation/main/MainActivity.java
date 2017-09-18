package com.matsyuk.wizardcase.presentation.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.matsyuk.wizardcase.R;
import com.matsyuk.wizardcase.common.ui.BackButtonListener;
import com.matsyuk.wizardcase.di.ComponentManager;
import com.matsyuk.wizardcase.presentation.activation.views.ActivationFragment;
import com.matsyuk.wizardcase.presentation.info.views.InfoFinishFragment;
import com.matsyuk.wizardcase.presentation.info.views.InfoStartFragment;
import com.matsyuk.wizardcase.presentation.license.views.LicenseFragment;
import com.matsyuk.wizardcase.wizards.main.MainWizardSmartRouter;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;

import static com.matsyuk.wizardcase.wizards.RouterConstants.ACTIVATION_SCREEN;
import static com.matsyuk.wizardcase.wizards.RouterConstants.INFO_FINISH_SCREEN;
import static com.matsyuk.wizardcase.wizards.RouterConstants.INFO_START_SCREEN;
import static com.matsyuk.wizardcase.wizards.RouterConstants.LICENSE_SCREEN;

public class MainActivity extends AppCompatActivity {

    @Inject
    NavigatorHolder navigatorHolder;

    @Inject
    MainWizardSmartRouter mainWizardSmartRouter;

    private Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.start_container) {

        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            if (screenKey.equals(INFO_START_SCREEN)) {
                return new InfoStartFragment();
            } else if (screenKey.equals(LICENSE_SCREEN)) {
                return new LicenseFragment();
            } else if (screenKey.equals(ACTIVATION_SCREEN)) {
                return new ActivationFragment();
            } else if (screenKey.equals(INFO_FINISH_SCREEN)) {
                return new InfoFinishFragment();
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
        ComponentManager.getInstance().getMainWizardComponent().inject(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.start_container);
        if (fragment != null
                && fragment instanceof BackButtonListener
                && ((BackButtonListener) fragment).onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        navigatorHolder.setNavigator(navigator);
        mainWizardSmartRouter.startWizard();
    }

    @Override
    public void onPause() {
        navigatorHolder.removeNavigator();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (isFinishing()) {
            ComponentManager.getInstance().clearWizardComponent();
        }
        super.onDestroy();
    }

}
