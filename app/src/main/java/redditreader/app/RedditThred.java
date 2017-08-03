package redditreader.app;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dkholodniak on 12.06.17.
 */

public class RedditThred {
    @SerializedName("title")
    private String name;
    @SerializedName("thumbnail")
    private String imagePath;
    @SerializedName("author")
    private String author;
    @SerializedName("created")
    private long created ;

    public long getHour() {
      long result=System.currentTimeMillis() / 1000 / 3600;
        return result;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public RedditThred(String name, String imagePath, String author,long created) {
        this.name = name;
        this.imagePath = imagePath;
        this.author = author;
        this.created = created;


    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RedditThred that = (RedditThred) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return imagePath != null ? imagePath.equals(that.imagePath) : that.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RedditThred{" +
                "name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
