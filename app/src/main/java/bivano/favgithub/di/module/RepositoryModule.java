package bivano.favgithub.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.provider.ContactsContract;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import bivano.favgithub.data.DataManager;
import bivano.favgithub.data.local.DbHelperImpl;
import bivano.favgithub.data.local.LocalDao;
import bivano.favgithub.data.local.LocalDatabase;
import bivano.favgithub.data.remote.NetworkApi;
import bivano.favgithub.data.remote.NetworkHelperImpl;
import bivano.favgithub.di.Local;
import bivano.favgithub.di.Remote;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by BimoV on 12/25/2017.
 */
@Module
public abstract class RepositoryModule {

    @Singleton
    @Provides
    static LocalDatabase provideDB(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class, "local.db").build();
    }

    @Singleton
    @Provides
    static LocalDao providesDao(LocalDatabase db){
        return db.localDao();
    }

    @Singleton
    @Provides
    static Executor providesExecutor(){
        return Executors.newFixedThreadPool(2);
    }

    @Singleton
    @Provides
    static CompositeDisposable provideDisposable(){
        return new CompositeDisposable();
    }

    @Singleton
    @Binds
    @Local
    abstract DataManager provideTasksLocalDataSource(DbHelperImpl dataSource);

    @Singleton
    @Binds
    @Remote
    abstract DataManager provideTasksRemoteDataSource(NetworkHelperImpl dataSource);

}
