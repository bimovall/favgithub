package bivano.favgithub.ui.activity.search;

import java.util.ArrayList;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by BimoV on 12/26/2017.
 */
@Module
public abstract class SearchModule {

    @Binds
    abstract SearchContract.Presenter providePresenter(SearchPresenter searchPresenter);

    @Provides
    static SearchAdapter providesAdapter(){
        return new SearchAdapter(new ArrayList<>());
    }
}
