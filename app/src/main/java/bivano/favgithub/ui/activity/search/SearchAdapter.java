package bivano.favgithub.ui.activity.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bivano.favgithub.R;
import bivano.favgithub.model.UserModel;
import bivano.favgithub.ui.activity.detail.DetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BimoV on 12/27/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<UserModel> searchResponses;

    public SearchAdapter(List<UserModel> searchResponses) {
        this.searchResponses = searchResponses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserModel item = searchResponses.get(position);
        holder.tvName.setText(item.getLogin());
    }

    @Override
    public int getItemCount() {
        return searchResponses.size();
    }

    public void addItems(List<UserModel> response) {
        searchResponses.clear();
        searchResponses.addAll(response);
        notifyDataSetChanged();
    }

    public void clearItems() {
        searchResponses.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.root_layout)
        public void onViewClicked(View view) {
            UserModel user = searchResponses.get(getAdapterPosition());
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("username",user.getLogin());
            intent.putExtras(bundle);
            view.getContext().startActivity(intent);
        }

    }
}
