package redditreader.app;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dkholodniak on 12.06.17.
 */

public class SubreditFragment extends Fragment {

    @NonNull
    private String subredditNameArg = "";
    private RecyclerView reddits;
    private RedditsAdapter adapter;
    private LoadTreads loadTreads;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.subredit_fragment,container,false);
        subredditNameArg = getArguments().getString("subredditName","");
        reddits = (RecyclerView) view.findViewById(R.id.reddits_list);
        reddits.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RedditsAdapter(getActivity());
        reddits.setAdapter(adapter);
        loadTreads = new LoadTreads();
        loadTreads.execute(subredditNameArg);




        //REMOVE
        //System.currentTimeMillis() / 1000;

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        adapter = null;
        loadTreads.cancel(true);
    }



    public static SubreditFragment CreateInstance(String subredditName){

        Bundle bundle = new Bundle();
        bundle.putString("subredditName",subredditName);
        SubreditFragment subreditFragment = new SubreditFragment();
        subreditFragment.setArguments(bundle);
        return subreditFragment;
    }
    class LoadTreads extends AsyncTask<String,Void,List<RedditThred>>{

        @Override
        protected List<RedditThred> doInBackground(String... voids) {
            return new SubRedditloader_Test().loadSubrettids(voids[0]);
        }

        @Override
        protected void onPostExecute(List<RedditThred> redditThreds) {
            super.onPostExecute(redditThreds);
            adapter.setRedditThreds(redditThreds);
        }
    }
}


