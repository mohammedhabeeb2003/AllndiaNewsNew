package pingala.com.navigationdrawer1.activity;


import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import pingala.com.navigationdrawer1.R;
import pingala.com.navigationdrawer1.adapter.CustomLanguageAdapter;
import pingala.com.navigationdrawer1.model.ListOfLanguage;

/**
 * A simple {@link Fragment} subclass.
 */
public class LanguageFragment extends Fragment {

   GridView gv_language;
    String url,title;
    ArrayList<String> newsLinks;
    ProgressDialog pd;
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

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReferenceFromUrl(url);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<ListOfLanguage> langHindi = new ArrayList();
                    newsLinks = new ArrayList();
                    for(DataSnapshot data: dataSnapshot.getChildren()){
                        String image =  data.child("Image").getValue(String.class);
                        String name =  data.child("Name").getValue(String.class);
                        ListOfLanguage la = new ListOfLanguage(name,image);
                        langHindi.add(la);

                    }
                    for(DataSnapshot data: dataSnapshot.getChildren()){

                        String links =  data.child("Web").getValue(String.class);
                        newsLinks.add(links);
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

            gv_language.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if(position==position) {

                        String links = newsLinks.get(position);
                        Log.e("Links",""+links);
                        Intent i = new Intent(getActivity(), TabActivity.class);
                        i.putExtra("Links",links);

                        startActivity(i);
                    }
                }
            });

        }
    }

}
