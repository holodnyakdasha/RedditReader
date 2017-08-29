package redditreader.app;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://reddit.com/";

    private RecyclerView mRecyclerView;

    private CompositeDisposable mCompositeDisposable;

    private RedditsAdapter mAdapter;

    private ArrayList<RedditThred> mRedditsArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchFormFragment()).addToBackStack(SearchFormFragment.class.getName()).commit();
        }

        // RedditThred obj = new RedditThred("Thread1", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Jupiter_and_its_shrunken_Great_Red_Spot.jpg/200px-Jupiter_and_its_shrunken_Great_Red_Spot.jpg","","");
        //Gson gson = new Gson();
        //  String data = gson.toJson(obj);
        //Log.d("GSON", gson.toJson(obj));

        //RedditThred obj2 = gson.fromJson(data, RedditThred.class);

        // Log.d("FROM_GSON", obj2.toString());

        mRecyclerView = (RecyclerView) findViewById(R.id.reddits_list);
        mCompositeDisposable = new CompositeDisposable();
      //  initRecyclerView();
        loadJSON();

    }


   // private void initRecyclerView() {

//
    //        mRecyclerView.setHasFixedSize(true);
      //      LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    //        mRecyclerView.setLayoutManager(layoutManager);

       // }





    private void loadJSON() {

        RedditInterface redditInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RedditInterface.class);

        mCompositeDisposable.add(
                redditInterface.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse,this::handleError));
    }


        private void handleResponse(List<RedditThred> RedditsList) {

        mRedditsArrayList = new ArrayList<>(RedditsList);
        mAdapter = new RedditsAdapter(mRedditsArrayList) ;
            mRecyclerView.setAdapter(mAdapter);
        }

    private void handleError(Throwable error) {

        Toast.makeText(this, "Error "+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

}
