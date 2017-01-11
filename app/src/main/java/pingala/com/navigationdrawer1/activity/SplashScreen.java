package pingala.com.navigationdrawer1.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mukesh.tinydb.TinyDB;

import java.util.ArrayList;
import java.util.List;

import pingala.com.navigationdrawer1.R;
import pingala.com.navigationdrawer1.adapter.NotificationAdapter;
import pingala.com.navigationdrawer1.model.NotificationItems;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FirebaseDatabase database;
    DatabaseReference myRef;
    ListView lv_lang;
    TinyDB tydb;
    List<NotificationItems> notificationItemsList;
    NotificationAdapter notificationAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lv_lang = (ListView) findViewById(R.id.lv_notification);
        notificationItemsList = new ArrayList<>();
        tydb = new TinyDB(this);
        NotificationItems ni = new NotificationItems("Hindi", tydb.getBoolean("HindiCheck"));
        notificationItemsList.add(ni);
        Log.e("Hindi", "=" + tydb.getBoolean("HindiCheck"));
        NotificationItems ni1 = new NotificationItems("English", tydb.getBoolean("EnglishCheck"));
        notificationItemsList.add(ni1);
        NotificationItems ni2 = new NotificationItems("Telugu", tydb.getBoolean("TeluguCheck"));
        notificationItemsList.add(ni2);
        NotificationItems ni3 = new NotificationItems("Tamil", tydb.getBoolean("TamilCheck"));
        notificationItemsList.add(ni3);
        NotificationItems ni4 = new NotificationItems("Bengali", tydb.getBoolean("BengaliCheck"));
        notificationItemsList.add(ni4);
        NotificationItems ni5 = new NotificationItems("Kannada", tydb.getBoolean("KannadaCheck"));
        notificationItemsList.add(ni5);
        NotificationItems ni6 = new NotificationItems("Gujarati", tydb.getBoolean("GujaratiCheck"));
        notificationItemsList.add(ni6);
        NotificationItems ni7 = new NotificationItems("Punjabi", tydb.getBoolean("PunjabiCheck"));
        notificationItemsList.add(ni7);
        NotificationItems ni8 = new NotificationItems("Assamese", tydb.getBoolean("AssameseCheck"));
        notificationItemsList.add(ni8);
        NotificationItems ni9 = new NotificationItems("Malayalam", tydb.getBoolean("MalayalamCheck"));
        notificationItemsList.add(ni9);
        NotificationItems ni10 = new NotificationItems("Marathi", tydb.getBoolean("MarathiCheck"));
        notificationItemsList.add(ni10);
        NotificationItems ni11 = new NotificationItems("Odia", tydb.getBoolean("OdiaCheck"));
        notificationItemsList.add(ni11);

        if (notificationItemsList == null && notificationItemsList.size() > 0) {
            setContentView(R.layout.activity_splash_screen);
            lv_lang = (ListView) findViewById(R.id.lv_notification);

            notificationAdapter = new NotificationAdapter(this, R.layout.custom_notification_toggle, notificationItemsList);
            lv_lang.setAdapter(notificationAdapter);

        } else {
            save();
        }
    }

    public void saveSharedPref() {

        tydb.putBoolean("HindiCheck", notificationItemsList.get(0).isToggle());
        tydb.putBoolean("EnglishCheck", notificationItemsList.get(1).isToggle());
        tydb.putBoolean("TeluguCheck", notificationItemsList.get(2).isToggle());
        tydb.putBoolean("TamilCheck", notificationItemsList.get(3).isToggle());
        tydb.putBoolean("BengaliCheck", notificationItemsList.get(4).isToggle());
        tydb.putBoolean("KannadaCheck", notificationItemsList.get(5).isToggle());
        tydb.putBoolean("GujaratiCheck", notificationItemsList.get(6).isToggle());
        tydb.putBoolean("PunjabiCheck", notificationItemsList.get(7).isToggle());
        tydb.putBoolean("AssameseCheck", notificationItemsList.get(8).isToggle());
        tydb.putBoolean("MalayalamCheck", notificationItemsList.get(9).isToggle());
        tydb.putBoolean("MarathiCheck", notificationItemsList.get(10).isToggle());
        tydb.putBoolean("OdiaCheck", notificationItemsList.get(11).isToggle());

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveSharedPref();

    }

    @Override
    protected void onPause() {
        super.onPause();
        saveSharedPref();

    }

    public List<NotificationItems> getNotificationList() {

        return notificationItemsList;

    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void save() {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}


