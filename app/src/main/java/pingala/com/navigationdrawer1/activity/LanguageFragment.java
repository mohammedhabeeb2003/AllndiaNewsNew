package pingala.com.navigationdrawer1.activity;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import pingala.com.navigationdrawer1.R;
import pingala.com.navigationdrawer1.adapter.CustomLanguageAdapter;
import pingala.com.navigationdrawer1.model.ChannelList;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageFragment extends Fragment {

   GridView gv_language;
    String url,title;
    ArrayList<String> newsLinks, liveLinks;
    ProgressDialog pd;
    FirebaseDatabase database;
    public LanguageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_langauge, container, false);
        url = getArguments().getString("Language");
        title = getArguments().getString("Title");
        Log.e("Title","="+title);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(title);

        gv_language = (GridView)v.findViewById(R.id.gridview_lang);
        new MyTask().execute();
        return v;
    }

    class MyTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setTitle("Loading");
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReferenceFromUrl(url);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<ChannelList> langHindi = new ArrayList();
                    newsLinks = new ArrayList();
                    liveLinks = new ArrayList();
                    for(DataSnapshot data: dataSnapshot.getChildren()){
                        String image =  data.child("Image").getValue(String.class);
                        String name =  data.child("Name").getValue(String.class);
                        String web = data.child("Web").getValue(String.class);
                        String live = data.child("Live").getValue(String.class);
                        ChannelList la = new ChannelList(name, image, web, live);
                        langHindi.add(la);

                    }

                    try{
                    CustomLanguageAdapter ca = new CustomLanguageAdapter(getActivity(),R.layout.custom_grid_view,langHindi);
                    gv_language.setAdapter(ca);
                    ca.notifyDataSetChanged();
                    }
                    catch (Exception e){
                        Log.e("AdapterChanges","="+e);
                    }
                    pd.dismiss();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);



        }
    }

}
