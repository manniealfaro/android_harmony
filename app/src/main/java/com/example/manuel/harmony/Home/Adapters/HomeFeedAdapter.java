package com.example.manuel.harmony.Home.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.manuel.harmony.Models.HomeFeedNew;
import com.example.manuel.harmony.R;

import java.util.ArrayList;

/**
 * Created by manuel on 30/11/2017.
 */

public class HomeFeedAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<HomeFeedNew> mDataSource;

    public HomeFeedAdapter(Context context, ArrayList<HomeFeedNew> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item

        View rowView = mInflater.inflate(R.layout.layout_home_news_feed_new, parent, false);

        // Get title element
        TextView titleTextView = (TextView) rowView.findViewById(R.id.recipe_list_title);

        // Get subtitle element
        TextView subtitleTextView = (TextView) rowView.findViewById(R.id.recipe_list_subtitle);

        // Get detail element
        //TextView detailTextView = (TextView) rowView.findViewById(R.id.recipe_list_detail);

        // Get detail element

        HomeFeedNew homeFeedNew = (HomeFeedNew) getItem(position);


        titleTextView.setText(homeFeedNew.title);
        subtitleTextView.setText(homeFeedNew.date);


        //Picasso.with(mContext).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);


        return rowView;
    }
}
