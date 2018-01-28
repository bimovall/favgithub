package bivano.favgithub.ui.activity.home;

import java.util.ArrayList;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by BimoV on 12/25/2017.
 */
@Module
public abstract class HomeModule {

    @Binds
    abstract HomeContract.Presenter providePresenter(HomePresenter presenter);

    @Provides
    static UserAdapter provideAdapter(){
        return new UserAdapter(new ArrayList<>());
    }

}
