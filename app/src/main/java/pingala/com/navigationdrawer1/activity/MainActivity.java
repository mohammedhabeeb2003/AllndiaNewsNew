package pingala.com.navigationdrawer1.activity;

import android.app.Activity;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import pingala.com.navigationdrawer1.R;
import pingala.com.navigationdrawer1.model.ListOfLanguage;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{

    private static String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment =(FragmentDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer,(DrawerLayout)findViewById(R.id.drawer_layout),toolbar);

        drawerFragment.setDrawerListener(this);
        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);

    }
    public int displayView(int position) {
        int count=0;
         Bundle b = new Bundle();

        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                title = getString(R.string.title_home);

                break;
            case 1:
                fragment = new LanguageFragment();
                title = getString(R.string.title_hindi);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Hindi");
                b.putString("Title",title);
                fragment.setArguments(b);

                break;
            case 2:
                fragment = new LanguageFragment();
                title = getString(R.string.title_english);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/English");
                b.putString("Title",title);
                fragment.setArguments(b);
                break;
            case 3:
                fragment = new LanguageFragment();
                title = getString(R.string.title_telugu);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Telugu");
                b.putString("Title",title);
                fragment.setArguments(b);
                break;
            case 4:
                fragment = new LanguageFragment();
                title = getString(R.string.title_marathi);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Marathi");
                b.putString("Title",title);
                fragment.setArguments(b);
                break;
            case 5:
                fragment = new LanguageFragment();
                title = getString(R.string.title_bengali);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Bengali");
                b.putString("Title",title);
                fragment.setArguments(b);
                break;
            case 6:
                fragment = new LanguageFragment();
                title = getString(R.string.title_kannada);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Kannada");
                b.putString("Title",title);
                fragment.setArguments(b);
                break;
            case 7:
                fragment = new LanguageFragment();
                title = getString(R.string.title_malayalam);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Malayalam");
                b.putString("Title",title);
                fragment.setArguments(b);
                break;
            case 8:
                fragment = new LanguageFragment();
                title = getString(R.string.title_punjabi);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Punjabi");
                b.putString("Title",title);
                fragment.setArguments(b);
                break;
            case 9:
                fragment = new LanguageFragment();
                title = getString(R.string.title_assamese);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Assame");
                b.putString("Title",title);
                fragment.setArguments(b);
                break;
            case 10:
                fragment = new LanguageFragment();
                title = getString(R.string.title_gujarati);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Gujarati");
                b.putString("Title",title);
                fragment.setArguments(b);
                break;
            case 11:
                fragment = new LanguageFragment();
                title = getString(R.string.title_urdu);
                b.putString("Language","https://allindianews-54021.firebaseio.com/Language/Urdu");
                b.putString("Title",title);
                fragment.setArguments(b);
                break;
            default:

                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_in_down, R.anim.slide_out_down, R.anim.slide_out_up);
            fragmentTransaction.replace(R.id.container_body, fragment);

            fragmentTransaction.commit();

            // set the toolbar title

        }
        return count;
    }

    @Override
    public void onBackPressed() {
        HomeFragment fragment = new HomeFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(getString(R.string.title_home));






    }
}
