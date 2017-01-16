package pingala.com.navigationdrawer1.activity;

/**
 * Created by Habeeb on 1/10/2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import pingala.com.navigationdrawer1.adapter.MyAdapter;
import pingala.com.navigationdrawer1.model.FeedItem;
import pingala.com.navigationdrawer1.model.VerticalSpace;

/**
 * Created by rishabh on 31-01-2016.
 */
class LoadingData extends AsyncTask<Void, Void, Void> {
    Context context;
    String address = "http://www.sciencemag.org/rss/news_current.xml";
    ProgressDialog progressDialog;
    ArrayList<FeedItem> feedItems;
    RecyclerView recyclerView;
    MyAdapter adapter;
    URL url;

    public LoadingData(Context context, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {



        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        progressDialog.dismiss();


    }

    @Override
    protected Void doInBackground(Void... params) {
        feedItems = new ArrayList<>();
        String json_url = "https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=latest&apiKey=20be3ce23c444051a34dff2ac6161a10";
        if (json_url != null) {
            try {
                URL url = new URL(json_url);
                URLConnection con = url.openConnection();
                HttpURLConnection http = (HttpURLConnection) con;
                http.setRequestMethod("GET");
                http.connect();

                InputStream is = http.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String JSON_STRING = br.readLine();

                JSONObject js = new JSONObject(JSON_STRING);
                JSONArray ja = js.getJSONArray("articles");

                for (int i = 0; i < ja.length(); i++) {
                    JSONObject json = ja.getJSONObject(i);
                    FeedItem fi = new FeedItem();

                    fi.setTitle(json.getString("title"));
                    fi.setDescription(json.getString("description"));
                    fi.setLink(json.getString("url"));
                    fi.setPubDate(json.getString("publishedAt"));
                    fi.setThumbnailUrl(json.getString("urlToImage"));
                    feedItems.add(fi);
                    Log.e("feedItem", "=" + feedItems.size());
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        adapter = new MyAdapter(context, feedItems);
        return null;
    }


}
