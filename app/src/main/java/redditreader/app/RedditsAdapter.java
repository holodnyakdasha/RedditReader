package redditreader.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkholodniak on 12.06.17.
 */

public class RedditsAdapter extends RecyclerView.Adapter<RedditsAdapter.ViewHolder> {
    private Context context;
    private List<RedditThred> redditThreds;

    //TODO: Данные в адаптер можно подкинуть и после его создания через setRedditThreads, не забудь дернуть адаптер шоб он обновился
    public RedditsAdapter(Context context) {
        this.context = context;
    }

    public RedditsAdapter(ArrayList<RedditThred> mRedditsArrayList) {

    }


    public void setRedditThreds(List<RedditThred> redditThreds) {
        this.redditThreds = redditThreds;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.reddit_item, viewGroup, false);
        return new ViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.getName().setText(redditThreds.get(i).getName());
        Glide.with(context).load(redditThreds.get(i).getImagePath()).into(viewHolder.thumbnail);
        viewHolder.getAuthor().setText(redditThreds.get(i).getAuthor());
        viewHolder.getHour().setText(context.getResources().getQuantityString(R.plurals.hours, (int) redditThreds.get(i).getHour(), (int) redditThreds.get(i).getHour()));
    }

    @Override
    public int getItemCount() {
        if (redditThreds != null) {
            return redditThreds.size();
        } else return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView thumbnail;
        private TextView name;
        private TextView author;
        private TextView hour;

        public TextView getHour() {
            return hour;
        }

        public TextView getAuthor() {
            return author;
        }

        public ImageView getThumbnail() {
            return thumbnail;
        }

        public TextView getName() {
            return name;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.reddit_thumbnail);
            name = (TextView) itemView.findViewById(R.id.reddit_name);
            author = (TextView) itemView.findViewById(R.id.reddit_author);
            hour = (TextView) itemView.findViewById(R.id.reddit_hour);


        }
    }

}
