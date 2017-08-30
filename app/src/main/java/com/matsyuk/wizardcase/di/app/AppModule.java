package com.matsyuk.wizardcase.di.app;

import com.matsyuk.wizardcase.business.auth.AuthInteractor;
import com.matsyuk.wizardcase.business.auth.AuthInteractorFake;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author e.matsyuk
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    public AuthInteractor provideAuthInteractor() {
        return new AuthInteractorFake();
    }

}
