package pingala.com.navigationdrawer1.model;

/**
 * Created by Habeeb on 1/6/2017.
 */

public class NotificationItems extends Object {

    String name;
    boolean toggle;
    String img_lang;

    public NotificationItems(String name, boolean toggle) {
        this.name = name;
        this.toggle = toggle;
    }

    public NotificationItems(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isToggle() {
        return toggle;
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }
}
