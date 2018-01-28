package bivano.favgithub.ui.activity.home;

import android.util.Log;

import javax.inject.Inject;

import bivano.favgithub.data.DataManagerImpl;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BimoV on 12/25/2017.
 */

class HomePresenter implements HomeContract.Presenter {

    private DataManagerImpl dataManager;
    private HomeContract.View view;

//    @Inject
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    HomePresenter(DataManagerImpl dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void setView(HomeContract.View view) {
        this.view = view;
    }

    @Override
    public void loadUser() {
        compositeDisposable.add(
                dataManager.getUsersDb()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(githubUsers -> {
                            if (githubUsers.size() > 0) {
                                view.showUsers(githubUsers);
                            } else {
                                view.showEmptyData();
                            }
                        }, throwable -> {
                            throwable.printStackTrace();
                            view.showEmptyData();
                        }));
    }

    @Override
    public void dispose() {
        if (compositeDisposable!=null && !compositeDisposable.isDisposed()) compositeDisposable.dispose();

    }
}
