package com.matsyuk.wizardcase.di;

import com.matsyuk.wizardcase.di.account_wizard.AccountWizardComponent;
import com.matsyuk.wizardcase.di.app.AppComponent;
import com.matsyuk.wizardcase.di.app.DaggerAppComponent;
import com.matsyuk.wizardcase.di.main_wizard.MainWizardComponent;

/**
 * @author e.matsyuk
 */
public class ComponentManager {

    private static volatile ComponentManager instance;

    private AppComponent appComponent;
    private MainWizardComponent mainWizardComponent;
    private AccountWizardComponent accountWizardComponent;

    public static ComponentManager getInstance() {
        if (instance == null) {
            synchronized (ComponentManager.class) {
                if (instance == null) {
                    instance = new ComponentManager();
                }
            }
        }
        return instance;
    }

    private ComponentManager() {}

    /**
     * AppComponent
     */

    public void initAppComponent() {
        appComponent = DaggerAppComponent.builder().build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    /**
     * MainWizardComponent
     */

    public MainWizardComponent getMainWizardComponent() {
        if (mainWizardComponent == null) {
            mainWizardComponent = getAppComponent()
                    .wizardComponentBuilder()
                    .build();
        }
        return mainWizardComponent;
    }

    public void clearWizardComponent() {
        mainWizardComponent = null;
    }

    /**
     * AccountWizardComponent
     */

    public AccountWizardComponent getAccountWizardComponent() {
        if (accountWizardComponent == null) {
            accountWizardComponent = getMainWizardComponent()
                    .accoutWizardComponentBuilder()
                    .build();
        }
        return accountWizardComponent;
    }

    public void clearAccountWizardComponent() {
        accountWizardComponent = null;
    }

}
