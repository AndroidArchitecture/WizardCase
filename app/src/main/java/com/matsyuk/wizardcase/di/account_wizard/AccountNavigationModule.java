package com.matsyuk.wizardcase.di.account_wizard;

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
public class AccountNavigationModule {

    private Cicerone<Router> cicerone;

    public AccountNavigationModule() {
        cicerone = Cicerone.create();
    }

    @Provides
    @AccountWizardScope
    @Named(ACCOUNT_WIZARD_ANNOTATION)
    Router provideRouter() {
        return cicerone.getRouter();
    }

    @Provides
    @AccountWizardScope
    @Named(ACCOUNT_WIZARD_ANNOTATION)
    NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

}
