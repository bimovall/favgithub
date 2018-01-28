package bivano.favgithub.data.local;

import android.util.Log;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import bivano.favgithub.data.DataManager;
import bivano.favgithub.model.GithubUser;
import bivano.favgithub.model.SearchResponse;
import bivano.favgithub.model.UserRepo;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by BimoV on 12/25/2017.
 */
@Singleton
public class DbHelperImpl implements DataManager {

    private LocalDao localDao;

    private Executor executor;

    @Inject
    DbHelperImpl(LocalDao localDao, Executor executor) {
        this.localDao = localDao;
        this.executor = executor;
    }

    @Override
    public void saveGithubUser(final GithubUser user) {
        executor.execute(() -> localDao.insertUser(user));
    }

    @Override
    public Maybe<List<GithubUser>> getUsersDb() {
        return localDao.getUsersDb();
    }

    @Override
    public Observable<SearchResponse> searchUser(String user) {
        return null;
    }

    @Override
    public Observable<GithubUser> getUser(String user) {
        return null;
    }

    @Override
    public Observable<List<UserRepo>> getUserRepo(String user) {
        return null;
    }

    @Override
    public Maybe<GithubUser> getUserByLogin(String login) {
        return localDao.getUserByLogin(login);
    }

    @Override
    public void deleteUser(String login) {
        executor.execute(() -> localDao.deleteUser(login));
    }
}
