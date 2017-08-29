package redditreader.app;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by dkholodniak on 19.06.17.
 */

public class SubRedditloader_Test {
   // private final static String Url = "https://www.reddit.com/r/Android.json";





    List<RedditThred> loadSubrettids(@NonNull String subredditName) {
        String   Url = "https://www.reddit.com/r/"+ subredditName +".json";
        Gson gson = new Gson();
        RedditResponse reddidResponse= gson.fromJson(getJSON(Url,5000),RedditResponse.class);

        List<RedditThred> result = new ArrayList<>();
        //TODO:  reddidResponse.getData() данных может и не быть
        for (RedditResponse.RedditData  item : reddidResponse.getData().getChildren())
            result.add(item.getData());
        return result;
    }
    public String getJSON(String url, int timeout) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();

                    synchronized (this) {
                     //   wait(3000);
                    }
                    return sb.toString();
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
