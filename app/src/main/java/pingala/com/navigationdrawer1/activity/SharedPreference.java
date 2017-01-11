package pingala.com.navigationdrawer1.activity;

/**
 * Created by Habeeb on 1/7/2017.
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pingala.com.navigationdrawer1.model.NotificationItems;

public class SharedPreference {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";
    Context context;

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<NotificationItems> favorites) {
        SharedPreferences settings;
        this.context = context;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, NotificationItems product) {
        List<NotificationItems> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<NotificationItems>();
        favorites.add(product);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, NotificationItems product) {
        ArrayList<NotificationItems> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(product);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<NotificationItems> getFavorites(Context context) {
        SharedPreferences settings;
        List<NotificationItems> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            NotificationItems[] favoriteItems = gson.fromJson(jsonFavorites,
                    NotificationItems[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<NotificationItems>(favorites);
        } else
            return null;

        return (ArrayList<NotificationItems>) favorites;
    }
}