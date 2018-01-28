package bivano.favgithub.model;

import java.util.List;

/**
 * Created by BimoV on 1/1/2018.
 */

public class UserWithRepos {
    private GithubUser user;
    private List<UserRepo> repo;

    public UserWithRepos(GithubUser user, List<UserRepo> repo) {
        this.user = user;
        this.repo = repo;
    }

    public GithubUser getUser() {
        return user;
    }

    public List<UserRepo> getRepo() {
        return repo;
    }
}
