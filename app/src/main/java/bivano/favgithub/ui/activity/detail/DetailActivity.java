package bivano.favgithub.ui.activity.detail;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import javax.inject.Inject;

import bivano.favgithub.R;
import bivano.favgithub.model.GithubUser;
import bivano.favgithub.model.UserRepo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by BimoV on 1/1/2018.
 */

public class DetailActivity extends DaggerAppCompatActivity implements DetailContract.View {

    @Inject
    DetailContract.Presenter presenter;

    @Inject
    RepoAdapter adapter;

    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_favorite)
    FloatingActionButton btnFavorite;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_title_toolbar)
    TextView tvTitleToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        presenter.setView(this);
        setToolbar();
        getData();
        initRecyclerView();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_arrow_left);
        getSupportActionBar().setTitle(null);

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    if (tvTitleToolbar.getVisibility() != View.VISIBLE) {
                        tvTitleToolbar.setVisibility(View.VISIBLE);
                        tvTitleToolbar.setText("Detail"); // show toolbar title
                        btnFavorite.hide();
                        isShow = true;
                    }
                } else if (isShow) {
                    tvTitleToolbar.setVisibility(View.GONE);
                    btnFavorite.show();
                    //toolbar.setBackground(ContextCompat.getDrawable(DetailActivity.this, R.drawable.background_transparent_toolbar));
                    //getSupportActionBar().setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            presenter.loadData(bundle.getString("username"));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_favorite)
    public void onViewClicked() {
        presenter.saveToDb();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showUser(GithubUser user) {
        tvName.setText(user.getName());
        tvLocation.setText(user.getLocation());
        tvUsername.setText(user.getLogin());
        Glide.with(this).load(user.getAvatar_url()).into(imgAvatar);
    }

    @Override
    public void showRepo(List<UserRepo> repo) {
        adapter.addRepo(repo);
    }


    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmptyRepo() {

    }

    @Override
    public void saveUser() {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        btnFavorite.setImageResource(R.mipmap.ic_launcher);
    }
    @Override
    public void deleteUser() {
        Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();
        btnFavorite.setImageResource(R.mipmap.ic_favorite);
    }

    @Override
    public void showIsAdded() {
        btnFavorite.setImageResource(R.mipmap.ic_launcher);
    }
}
