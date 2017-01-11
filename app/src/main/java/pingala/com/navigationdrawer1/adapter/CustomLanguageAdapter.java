package pingala.com.navigationdrawer1.adapter;

import android.content.Context;
import android.content.Intent;
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
import pingala.com.navigationdrawer1.activity.NewsLive;
import pingala.com.navigationdrawer1.model.ChannelList;

/**
 * Created by Habeeb on 12/29/2016.
 */


public class CustomLanguageAdapter extends ArrayAdapter {

    List<ChannelList> languageList;
    Context context;
    int resource;
    LayoutInflater layoutInflater;


    public CustomLanguageAdapter(Context context, int resource, List<ChannelList> languageList) {
        super(context, resource,languageList);
        this.context=context;
        this.languageList = languageList;
        this.resource = resource;
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        convertView = layoutInflater.inflate(resource, null);
        TextView tv_language = (TextView) convertView.findViewById(R.id.tv_language);
        ImageView img_langauge = (ImageView) convertView.findViewById(R.id.img_language);
        ImageView img_live = (ImageView) convertView.findViewById(R.id.btn_live);
        ImageView img_news = (ImageView) convertView.findViewById(R.id.btn_news);

        Log.e("position", "=" + position);
        img_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NewsLive.class);
                final String name = languageList.get(position).getName();
                final String news = languageList.get(position).getWeb();
                i.putExtra("Links", news);
                i.putExtra("Channel", name);
                Log.e("Name", "Channel" + name);
                context.startActivity(i);
            }
        });

        img_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NewsLive.class);
                final String name = languageList.get(position).getName();
                final String live = languageList.get(position).getLive();
                i.putExtra("Links", live);
                i.putExtra("Channel", name);
                context.startActivity(i);
            }
        });

        try {
            tv_language.setText(languageList.get(position).getName());

            Picasso.with(context).load(languageList.get(position).getImage()).into(img_langauge);

        } catch (Exception e) {

            Log.e("Adapter", "" + e);
        }

        return convertView;
    }


}
