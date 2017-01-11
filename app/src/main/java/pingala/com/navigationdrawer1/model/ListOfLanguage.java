package pingala.com.navigationdrawer1.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Habeeb on 12/29/2016.
 */

public class ListOfLanguage implements Parcelable{

    public static final Creator<ListOfLanguage> CREATOR = new Creator<ListOfLanguage>() {
        @Override
        public ListOfLanguage createFromParcel(Parcel in) {
            return new ListOfLanguage(in);
        }

        @Override
        public ListOfLanguage[] newArray(int size) {
            return new ListOfLanguage[size];
        }
    };
    String name;
    String image;
    String web;
    String live;

    public ListOfLanguage(String name, String image, String web, String live) {
        this.name = name;
        this.image = image;
        this.web = web;
        this.live = live;
    }

    public ListOfLanguage(String name, String image) {
        this.name = name;
        this.image = image;
    }

    protected ListOfLanguage(Parcel in) {
        name = in.readString();
        image = in.readString();
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
    }
}
