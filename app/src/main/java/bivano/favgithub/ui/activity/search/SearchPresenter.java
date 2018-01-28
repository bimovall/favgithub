package bivano.favgithub.ui.activity.search;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import bivano.favgithub.data.DataManagerImpl;
import bivano.favgithub.model.SearchResponse;
import bivano.favgithub.model.UserModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BimoV on 12/26/2017.
 */

class SearchPresenter implements SearchContract.Presenter {


    private DataManagerImpl dataManager;
    private SearchContract.View view;


    //@Inject
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    SearchPresenter(DataManagerImpl dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void setView(SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(String keyword) {

        compositeDisposable.add(dataManager.searchUser(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<SearchResponse>() {
                    @Override
                    public void onNext(SearchResponse searchResponse) {
                        List<UserModel> userModels = new ArrayList<>();
                        for (int i = 0; i < searchResponse.getItems().size(); i++) {
                            UserModel item = new UserModel();
                            item.setAvatar_url(searchResponse.getItems().get(i).getAvatar_url());
                            item.setId(searchResponse.getItems().get(i).getId());
                            item.setLogin(searchResponse.getItems().get(i).getLogin());
                            userModels.add(item);

                        }
                        for (int i = 0; i < userModels.size(); i++) {
                            Log.d("item", "onNext: " + userModels.get(i).getLogin());

                        }
                        view.showUser(userModels);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void addToFavorite() {

    }

    @Override
    public void dispose() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }
}
