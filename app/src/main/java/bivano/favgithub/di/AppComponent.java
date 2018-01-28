package bivano.favgithub.di;

import android.app.Application;

import javax.inject.Singleton;

import bivano.favgithub.di.module.AppModule;
import bivano.favgithub.di.module.BuilderModule;
import bivano.favgithub.di.module.NetworkModule;
import bivano.favgithub.di.module.RepositoryModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by BimoV on 12/19/2017.
 */
@Singleton
@Component(modules = {AppModule.class, AndroidSupportInjectionModule.class, RepositoryModule.class,
        BuilderModule.class, NetworkModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
