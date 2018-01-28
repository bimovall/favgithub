package bivano.favgithub.di.module;

import bivano.favgithub.ui.activity.detail.DetailActivity;
import bivano.favgithub.ui.activity.detail.DetailModule;
import bivano.favgithub.ui.activity.home.HomeActivity;
import bivano.favgithub.ui.activity.home.HomeModule;
import bivano.favgithub.ui.activity.search.SearchActivity;
import bivano.favgithub.ui.activity.search.SearchModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by BimoV on 12/25/2017.
 */
@Module
public abstract class BuilderModule {

    @ContributesAndroidInjector(modules = HomeModule.class)
    abstract HomeActivity homeActivity();

    @ContributesAndroidInjector(modules = SearchModule.class)
    abstract SearchActivity searchActivity();

    @ContributesAndroidInjector(modules = DetailModule.class)
    abstract DetailActivity detailActivity();

}
