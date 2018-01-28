package bivano.favgithub.data;

import java.util.List;

import bivano.favgithub.model.GithubUser;
import bivano.favgithub.model.SearchResponse;
import bivano.favgithub.model.UserRepo;
import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Created by BimoV on 12/25/2017.
 */

public interface DataManager {

    void saveGithubUser(GithubUser user);

    Maybe<List<GithubUser>> getUsersDb();

    Observable<SearchResponse> searchUser(String user);

    Observable<GithubUser>getUser(String user);

    Observable<List<UserRepo>> getUserRepo(String user);

    Maybe<GithubUser>getUserByLogin(String login);

    void deleteUser(String login);
}
