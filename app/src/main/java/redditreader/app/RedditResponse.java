package redditreader.app;

import java.util.List;

/**
 * Created by dkholodniak on 20.06.17.
 */

public class RedditResponse {
    private DataResponse data;

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }

    static class DataResponse{

        private List<RedditData> children;

        public List<RedditData> getChildren() {
            return children;
        }

        public void setChildren(List<RedditData> children) {
            this.children = children;
        }
    }

    static class RedditData{
        private RedditThred data;

        public RedditThred getData() {
            return data;
        }

        public void setData(RedditThred data) {
            this.data = data;
        }
    }
}
