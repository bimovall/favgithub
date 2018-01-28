package bivano.favgithub.ui.activity.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import bivano.favgithub.R;
import bivano.favgithub.model.UserRepo;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by BimoV on 1/2/2018.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private List<UserRepo> repoList;

    public RepoAdapter(List<UserRepo> repoList) {
        this.repoList = repoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        UserRepo repo = repoList.get(position);
        holder.tvFullname.setText(repo.getFull_name());
        holder.tvName.setText(repo.getName());
        holder.tvDesc.setText(repo.getDescription());
        holder.tvDate.setText(repo.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public void addRepo(List<UserRepo>userRepos){
        repoList.addAll(userRepos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_fullname)
        TextView tvFullname;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.tv_date)
        TextView tvDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
