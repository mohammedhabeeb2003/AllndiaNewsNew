package pingala.com.navigationdrawer1.activity;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.*;

import pingala.com.navigationdrawer1.R;
import pingala.com.navigationdrawer1.adapter.CustomLanguageAdapter;
import pingala.com.navigationdrawer1.model.ListOfLanguage;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef;
    GridView gv_home;
    ProgressDialog pd;
    List<ListOfLanguage> langList;
    CustomLanguageAdapter ca;
    ListOfLanguage la;
    FragmentManager fragmentManager;
    MainActivity activity;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        gv_home = (GridView)rootView.findViewById(R.id.gridview_home);

        new MyTask().execute();
        // Inflate the layout for this fragment
        return rootView;
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
          langList = new ArrayList<>();
            database = FirebaseDatabase.getInstance();
            myRef = database.getReferenceFromUrl("https://allindianews-54021.firebaseio.com/Language/ListofLanguage");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for( DataSnapshot data: dataSnapshot.getChildren()){

                        String image =  data.child("Image").getValue(String.class);
                        String name =  data.child("Name").getValue(String.class);
                        la = new ListOfLanguage(name,image);
                        langList.add(la);

                    }
try {
    ca = new CustomLanguageAdapter(getActivity(), R.layout.custom_grid_view, langList);
    gv_home.setAdapter(ca);
}
catch (Exception e){
    Log.e("Adapter","Error"+e);
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



            gv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    displayView(position);
                }
            });

        }
    }
    public void displayView(int position) {
        int count=0;
        Bundle b = new Bundle();
        LanguageFragment fragment = new LanguageFragment();

        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                 android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Hindi");
                b.putString("Title",getString(R.string.title_hindi));
                fragment.setArguments(b);

                break;
            case 1:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/English");
                b.putString("Title",getString(R.string.title_english));
                fragment.setArguments(b);
                break;
            case 2:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Telugu");
                b.putString("Title",getString(R.string.title_telugu));
                fragment.setArguments(b);
                break;
            case 3:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Marathi");
                b.putString("Title",getString(R.string.title_marathi));
                fragment.setArguments(b);
                break;
            case 4:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Bengali");
                b.putString("Title",getString(R.string.title_bengali));
                fragment.setArguments(b);
                break;
            case 5:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Kannada");
                b.putString("Title",getString(R.string.title_kannada));
                fragment.setArguments(b);
                break;
            case 6:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Malayalam");
                b.putString("Title",getString(R.string.title_malayalam));
                fragment.setArguments(b);
                break;
            case 7:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Punjabi");
                b.putString("Title",getString(R.string.title_punjabi));
                fragment.setArguments(b);
                break;
            case 8:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Assame");
                b.putString("Title",getString(R.string.title_assamese));
                fragment.setArguments(b);
                break;
            case 9:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Gujarati");
                b.putString("Title",getString(R.string.title_gujarati));
                fragment.setArguments(b);
                break;
            case 10:
                fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out).replace(R.id.container_body,fragment).commit();
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Urdu");
                b.putString("Title",getString(R.string.title_urdu));
                fragment.setArguments(b);
                break;
            default:
                break;
        }

        if (fragment != null) {



            // set the toolbar title

        }

    }

}
