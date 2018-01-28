package bivano.favgithub.ui.activity.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import bivano.favgithub.R;
import bivano.favgithub.model.GithubUser;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BimoV on 12/25/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<GithubUser> userList;

    public UserAdapter(List<GithubUser> userList) {
        this.userList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubUser user = userList.get(position);
        holder.tvName.setText(user.getName());
        holder.tvUsername.setText(user.getLogin());
        holder.tvLocation.setText(user.getLocation());
        holder.tvRepo.setText("Public Repository : " + user.getPublic_repos());
        Glide.with(holder.itemView.getContext()).load(user.getAvatar_url()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void addUser(List<GithubUser> users) {
        userList.addAll(users);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.avatar)
        ImageView avatar;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_repo)
        TextView tvRepo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.root_layout)
        public void onViewClicked() {
        }
    }
}
