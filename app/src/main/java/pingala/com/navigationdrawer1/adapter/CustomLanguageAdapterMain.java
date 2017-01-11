package pingala.com.navigationdrawer1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import pingala.com.navigationdrawer1.R;
import pingala.com.navigationdrawer1.model.ListOfLanguage;

/**
 * Created by Habeeb on 12/29/2016.
 */


public class CustomLanguageAdapterMain extends ArrayAdapter {

    List<ListOfLanguage> languageList;
    Context context;
    int resource;
    LayoutInflater layoutInflater;
    ViewHolder holder;

    public CustomLanguageAdapterMain(Context context, int resource, List<ListOfLanguage> languageList) {
        super(context, resource, languageList);
        this.context = context;
        this.languageList = languageList;
        this.resource = resource;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        holder = new ViewHolder();
        Log.e("position", "=" + position);
        if (v == null) {
            v = layoutInflater.inflate(resource, null);
            holder.tv_language = (TextView) v.findViewById(R.id.tv_language);
            holder.img_langauge = (ImageView) v.findViewById(R.id.img_language);


            v.setTag(holder);
        } else {

            holder = (ViewHolder) v.getTag();

        }
        try {
            holder.tv_language.setText(languageList.get(position).getName());
            Picasso.with(context).load(languageList.get(position).getImage()).into(holder.img_langauge);
        } catch (Exception e) {
            Log.e("InsideAdapter", "Error" + e);
        }

        return v;
    }

    static class ViewHolder {

        ImageView img_langauge;
        TextView tv_language;
        ImageView img_eNews;
        ImageView img_live;

    }
}
