package bivano.favgithub.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Module;

/**
 * Created by BimoV on 12/19/2017.
 */
@Module
public abstract class AppModule {

    @Binds
    abstract Context provideContext(Application app);
}
