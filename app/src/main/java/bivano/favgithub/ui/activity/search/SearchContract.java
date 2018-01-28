package bivano.favgithub.ui.activity.search;

import java.util.List;

import bivano.favgithub.model.UserModel;

/**
 * Created by BimoV on 12/26/2017.
 */

public interface SearchContract {

    interface View {
        void showUser(List<UserModel> response);

        void showLoading();

        void hideLoading();

        void clearUser();

        void errorLoadData();

        void showSuccessAdd();

    }

    interface Presenter {

        void setView(View view);

        void loadData(String keyword);


        void addToFavorite();

        void dispose();
    }
}
