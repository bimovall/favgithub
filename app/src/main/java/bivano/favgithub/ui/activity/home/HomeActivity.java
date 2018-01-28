package bivano.favgithub.ui.activity.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import bivano.favgithub.R;
import bivano.favgithub.model.GithubUser;
import bivano.favgithub.ui.activity.search.SearchActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by BimoV on 12/24/2017.
 */

public class HomeActivity extends DaggerAppCompatActivity implements HomeContract.View {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    @Inject
    HomeContract.Presenter presenter;

    @Inject
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        presenter.setView(this);
        setToolbar();
        presenter.loadUser();
        initRecyclerView();

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
    }

    private void initRecyclerView() {
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(userAdapter);
    }


    @Override
    public void showUsers(List<GithubUser> githubUsers) {
        userAdapter.addUser(githubUsers);
        recyclerview.setVisibility(View.VISIBLE);
        tvEmpty.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyData() {
        recyclerview.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        startActivity(new Intent(this, SearchActivity.class));
    }
}
