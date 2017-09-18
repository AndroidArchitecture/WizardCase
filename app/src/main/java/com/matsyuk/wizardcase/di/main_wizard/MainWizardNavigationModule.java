package com.matsyuk.wizardcase.di.main_wizard;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

import static com.matsyuk.wizardcase.di.DiConstants.*;

/**
 * @author e.matsyuk
 */
@Module
public class MainWizardNavigationModule {

    private Cicerone<Router> cicerone;

    public MainWizardNavigationModule() {
        cicerone = Cicerone.create();
    }

    @MainWizardScope
    @Provides
    @Named(MAIN_WIZARD_ANNOTATION)
    Router provideRouter() {
        return cicerone.getRouter();
    }

    @MainWizardScope
    @Provides
    @Named(MAIN_WIZARD_ANNOTATION)
    NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

}
