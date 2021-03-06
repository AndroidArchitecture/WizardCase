package com.matsyuk.wizardcase.di.app;

import com.matsyuk.wizardcase.di.wizard.WizardComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author e.matsyuk
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    WizardComponent.Builder wizardComponentBuilder();
}
