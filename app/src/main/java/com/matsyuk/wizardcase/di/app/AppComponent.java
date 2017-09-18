package com.matsyuk.wizardcase.di.app;

import com.matsyuk.wizardcase.di.main_wizard.MainWizardComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author e.matsyuk
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    MainWizardComponent.Builder wizardComponentBuilder();
}
