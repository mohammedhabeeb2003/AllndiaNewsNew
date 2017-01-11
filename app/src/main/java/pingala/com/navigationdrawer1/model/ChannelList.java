package pingala.com.navigationdrawer1.model;

/**
 * Created by Habeeb on 1/9/2017.
 */

public class ChannelList {

    String name;
    String image;
    String web;
    String live;

    public ChannelList(String name, String image, String web, String live) {
        this.name = name;
        this.image = image;
        this.web = web;
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
}
