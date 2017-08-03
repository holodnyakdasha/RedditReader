package redditreader.app;

import java.util.List;

import retrofit2.http.GET;
import io.reactivex.Observable;


public interface RedditInterface {
    @GET("/reddit")
     Observable <List<RedditThred>> register();
}

