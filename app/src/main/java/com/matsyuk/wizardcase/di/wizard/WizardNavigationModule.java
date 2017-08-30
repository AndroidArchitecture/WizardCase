package com.matsyuk.wizardcase.di.wizard;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

/**
 * @author e.matsyuk
 */
@Module
public class WizardNavigationModule {

    private Cicerone<Router> cicerone;

    public WizardNavigationModule() {
        cicerone = Cicerone.create();
    }

    @WizardScope
    @Provides
    Router provideRouter() {
        return cicerone.getRouter();
    }

    @WizardScope
    @Provides
    NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

}
