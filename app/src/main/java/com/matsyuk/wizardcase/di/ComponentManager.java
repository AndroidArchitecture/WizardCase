package com.matsyuk.wizardcase.di;

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

    public void initAppComponent() {
        appComponent = DaggerAppComponent.builder().build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public MainWizardComponent getMainMainComponent() {
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

}
