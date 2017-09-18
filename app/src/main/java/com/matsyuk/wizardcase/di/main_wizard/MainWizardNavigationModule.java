package com.matsyuk.wizardcase.di.main_wizard;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

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
    Router provideRouter() {
        return cicerone.getRouter();
    }

    @MainWizardScope
    @Provides
    NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

}
