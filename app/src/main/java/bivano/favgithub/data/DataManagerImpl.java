package bivano.favgithub.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import bivano.favgithub.model.GithubUser;
import bivano.favgithub.model.SearchResponse;
import bivano.favgithub.di.Local;
import bivano.favgithub.di.Remote;
import bivano.favgithub.model.UserRepo;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by BimoV on 12/25/2017.
 */
@Singleton
public class DataManagerImpl implements DataManager {


    private DataManager dbManager;

    private DataManager networkManager;

    @Inject
    DataManagerImpl(@Local DataManager dbHelper, @Remote DataManager networkManager){
        this.dbManager = dbHelper;
        this.networkManager = networkManager;
    }

    @Override
    public void saveGithubUser(GithubUser user) {
        dbManager.saveGithubUser(user);
    }

    @Override
    public Maybe<List<GithubUser>> getUsersDb() {
        return dbManager.getUsersDb();
    }

    @Override
    public Observable<SearchResponse> searchUser(String user) {
        return networkManager.searchUser(user);
    }

    @Override
    public Observable<GithubUser> getUser(String user) {
        Log.d("CALLED", "getUser: C ALCALLA");
        return networkManager.getUser(user);
    }

    @Override
    public Observable<List<UserRepo>> getUserRepo(String user) {
        return networkManager.getUserRepo(user);
    }

    @Override
    public Maybe<GithubUser> getUserByLogin(String login) {
        return dbManager.getUserByLogin(login);
    }

    @Override
    public void deleteUser(String login) {
        dbManager.deleteUser(login);
    }
}
