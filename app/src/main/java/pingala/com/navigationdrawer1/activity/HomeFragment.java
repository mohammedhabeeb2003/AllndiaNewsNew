package pingala.com.navigationdrawer1.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pingala.com.navigationdrawer1.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        LoadingData loadingData = new LoadingData(getActivity(), recyclerView);
        loadingData.execute();
        // Inflate the layout for this fragment
        return rootView;
    }

  /*  class LoadingData1 extends AsyncTask<Void, Void, Void> {
        Context context;
        String address = "http://www.sciencemag.org/rss/news_current.xml";
        ProgressDialog progressDialog;
        ArrayList<FeedItem> feedItems;


        @Override
        protected void onPreExecute() {
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            MyAdapter adapter = new MyAdapter(context, feedItems);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.addItemDecoration(new VerticalSpace(50));
            recyclerView.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... params) {
            feedItems = new ArrayList<>();
            String json_url = "https://newsapi.org/v1/articles?source=the-times-of-india&sortBy=latest&apiKey=20be3ce23c444051a34dff2ac6161a10";
            if (json_url!= null) {
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
                        fi.setThumbnailUrl("urlToImage");
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
            return null;
        }
    }*/
}
