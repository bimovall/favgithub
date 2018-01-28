package bivano.favgithub.data.remote;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import bivano.favgithub.data.DataManager;
import bivano.favgithub.model.GithubUser;
import bivano.favgithub.model.SearchResponse;
import bivano.favgithub.model.UserRepo;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by BimoV on 12/26/2017.
 */
@Singleton
public class NetworkHelperImpl implements DataManager {

    private NetworkApi networkApi;

    @Inject
    NetworkHelperImpl(NetworkApi networkApi) {
        this.networkApi = networkApi;
    }

    @Override
    public void saveGithubUser(GithubUser user) {

    }

    @Override
    public Maybe<List<GithubUser>> getUsersDb() {
        return null;
    }

    @Override
    public Observable<SearchResponse> searchUser(String user) {
        return networkApi.searchUser(user);
    }

    @Override
    public Observable<GithubUser> getUser(String user) {
        return networkApi.getUser(user);
    }

    @Override
    public Observable<List<UserRepo>> getUserRepo(String user) {
        return networkApi.getUserRepo(user);
    }

    @Override
    public Maybe<GithubUser> getUserByLogin(String login) {
        return null;
    }

    @Override
    public void deleteUser(String login) {

    }
}
