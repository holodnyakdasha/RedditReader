package redditreader.app;

import java.util.List;

/**
 * Created by dkholodniak on 19.06.17.
 */

public class SubReddidResponse {
    private List<RedditThred> data;

    public List<RedditThred> getData() {
        return data;
    }

    public void setData(List<RedditThred> data) {
        this.data = data;
    }
}
