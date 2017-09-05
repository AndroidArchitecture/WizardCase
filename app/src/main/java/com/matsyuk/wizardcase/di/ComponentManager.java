package com.matsyuk.wizardcase.di;

import com.matsyuk.wizardcase.di.account_wizard.AccountWizardComponent;
import com.matsyuk.wizardcase.di.app.AppComponent;
import com.matsyuk.wizardcase.di.app.DaggerAppComponent;
import com.matsyuk.wizardcase.di.main_wizard.WizardComponent;

/**
 * @author e.matsyuk
 */
public class ComponentManager {

    private static volatile ComponentManager instance;

    private AppComponent appComponent;
    private WizardComponent wizardComponent;
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
     * WizardComponent
     */

    public WizardComponent getWizardComponent() {
        if (wizardComponent == null) {
            wizardComponent = getAppComponent()
                    .wizardComponentBuilder()
                    .build();
        }
        return wizardComponent;
    }

    public void clearWizardComponent() {
        wizardComponent = null;
    }

    /**
     * AccountWizardComponent
     */

    public AccountWizardComponent getAccountWizardComponent() {
        if (accountWizardComponent == null) {
            accountWizardComponent = getWizardComponent()
                    .accoutWizardComponentBuilder()
                    .build();
        }
        return accountWizardComponent;
    }

    public void clearAccountWizardComponent() {
        accountWizardComponent = null;
    }

}
