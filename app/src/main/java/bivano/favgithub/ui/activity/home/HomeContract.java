package bivano.favgithub.ui.activity.home;

import java.util.List;

import bivano.favgithub.model.GithubUser;

/**
 * Created by BimoV on 12/24/2017.
 */

public interface HomeContract {

    interface View {
        void showUsers(List<GithubUser>githubUsers);

        void showEmptyData();
    }

    interface Presenter {

        void setView(HomeContract.View view);

        void loadUser();

        void dispose();

    }
}
