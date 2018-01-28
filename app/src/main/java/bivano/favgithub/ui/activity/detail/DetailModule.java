package bivano.favgithub.ui.activity.detail;

import java.util.ArrayList;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by BimoV on 1/1/2018.
 */
@Module
public abstract class DetailModule {

    @Binds
    abstract DetailContract.Presenter providePresenter(DetailPresenter presenter);

    @Provides
    static RepoAdapter provideAdapter(){
        return new RepoAdapter(new ArrayList<>());
    }
}
