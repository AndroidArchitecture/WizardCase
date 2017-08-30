package com.matsyuk.wizardcase.di;

import com.matsyuk.wizardcase.di.app.AppComponent;
import com.matsyuk.wizardcase.di.wizard.WizardComponent;

/**
 * @author e.matsyuk
 */
public class ComponentManager {

    private static volatile ComponentManager instance;

    private AppComponent appComponent;
    private WizardComponent wizardComponent;

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

    public WizardComponent getMainComponent() {
        if (wizardComponent == null) {
            wizardComponent = getAppComponent()
                    .wizardComponentBuilder()
                    .build();
        }
        return wizardComponent;
    }

}
