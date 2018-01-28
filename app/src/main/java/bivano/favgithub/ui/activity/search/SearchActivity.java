package bivano.favgithub.ui.activity.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import bivano.favgithub.R;
import bivano.favgithub.model.UserModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by BimoV on 12/25/2017.
 */

public class SearchActivity extends DaggerAppCompatActivity implements SearchContract.View {

    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_error)
    TextView tvError;

    @Inject
    SearchContract.Presenter presenter;

    @Inject
    SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        presenter.setView(this);
        initRecyclerView();

        RxTextView.textChangeEvents(etSearch)
                .debounce(200, TimeUnit.MILLISECONDS)
                .map(textViewTextChangeEvent -> textViewTextChangeEvent.text().toString().trim())
                .filter(s -> s.length() > 2)
                .subscribe(s -> presenter.loadData(s));
    }

    private void initRecyclerView() {
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dispose();
    }

    @Override
    public void showUser(List<UserModel> response) {
        adapter.addItems(response);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void clearUser() {
        adapter.clearItems();
    }

    @Override
    public void errorLoadData() {
        tvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSuccessAdd() {

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
