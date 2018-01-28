package bivano.favgithub.ui.activity.detail;


import javax.inject.Inject;

import bivano.favgithub.data.DataManagerImpl;
import bivano.favgithub.model.GithubUser;
import bivano.favgithub.model.UserWithRepos;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BimoV on 1/1/2018.
 */

class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View view;
    private DataManagerImpl dataManager;
    private GithubUser githubUser;
    private boolean isAdded = false;

    @Inject
    CompositeDisposable disposable;

    @Inject
    DetailPresenter(DataManagerImpl dataManager) {
        this.dataManager = dataManager;
    }


    @Override
    public void setView(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void loadData(String user) {
        Observable<GithubUser> a = dataManager.getUser(user);

        disposable.add(
                a.concatMap(
                        (Function<GithubUser, ObservableSource<?>>) user1 -> dataManager.getUserByLogin(user)
                                .map(GithubUser::getId)
                                .contains(user1.getId())
                                .toObservable())
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .zipWith(a, (o, user1) -> {
                            if (o.equals(true)) {
                                isAdded = true;
                                view.showIsAdded();
                            }
                            return user1;
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .zipWith(dataManager.getUserRepo(user), UserWithRepos::new)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<UserWithRepos>() {
                            @Override
                            public void onNext(UserWithRepos userWithRepos) {
                                githubUser = userWithRepos.getUser();
                                view.showRepo(userWithRepos.getRepo());
                                view.showUser(userWithRepos.getUser());
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
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) disposable.dispose();
    }

    @Override
    public void saveToDb() {
        if (!isAdded) {
            dataManager.saveGithubUser(githubUser);
            view.saveUser();
            isAdded = true;
        } else {
            dataManager.deleteUser(githubUser.getLogin());
            view.deleteUser();
            isAdded = false;
        }
    }
}
