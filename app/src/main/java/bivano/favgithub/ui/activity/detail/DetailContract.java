package bivano.favgithub.ui.activity.detail;

import java.util.List;

import bivano.favgithub.model.GithubUser;
import bivano.favgithub.model.UserRepo;

/**
 * Created by BimoV on 1/1/2018.
 */

public interface DetailContract {

    interface View {

        void showLoading();

        void showUser(GithubUser user);

        void showRepo(List<UserRepo> repo);

        void hideLoading();

        void showEmptyRepo();

        void saveUser();

        void deleteUser();

        void showIsAdded();


    }

    interface Presenter {

        void setView(View view);

        void loadData(String user);

        void dispose();

        void saveToDb();
    }
}
