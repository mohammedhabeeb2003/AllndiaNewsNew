package pingala.com.navigationdrawer1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import pingala.com.navigationdrawer1.R;
import pingala.com.navigationdrawer1.model.NotificationItems;

/**
 * Created by Habeeb on 1/6/2017.
 */

public class NotificationAdapter extends ArrayAdapter {
    List<NotificationItems> notifyItems;
    Context context;
    int resource;
    LayoutInflater layoutInflater;
    ViewHolderNotification holder;

    public NotificationAdapter(Context context, int resource, List<NotificationItems> notifyItems) {
        super(context, resource, notifyItems);
        this.context = context;
        this.notifyItems = notifyItems;
        this.resource = resource;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        holder = new ViewHolderNotification();
        if (v == null) {
            v = layoutInflater.inflate(resource, null);
            holder.tv_lang = (TextView) v.findViewById(R.id.tv_notify_name);
            holder.cb_lang = (CheckBox) v.findViewById(R.id.cb_notification);
            holder.cb_lang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox) v;
                    notifyItems.get(position).setToggle(checkBox.isChecked());

                }
            });

            v.setTag(holder);
        } else {
            holder = (ViewHolderNotification) v.getTag();
        }

        holder.tv_lang.setText(notifyItems.get(position).getName());
        holder.cb_lang.setChecked(notifyItems.get(position).isToggle());

        return v;


    }

    static class ViewHolderNotification {

        CheckBox cb_lang;
        TextView tv_lang;

    }
}
