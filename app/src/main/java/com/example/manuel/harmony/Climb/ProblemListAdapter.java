package com.example.manuel.harmony.Climb;

/**
 * Created by manuel on 19/12/2017.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.manuel.harmony.R;
import com.example.manuel.harmony.Objects.Problem;

import java.util.ArrayList;

/**
 * Created by manuel on 27/11/2017.
 */

public class ProblemListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Problem> mDataSource;

    public ProblemListAdapter(Context context, ArrayList<Problem> items) {
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

        View rowView = mInflater.inflate(R.layout.layout_problem_list_item, parent, false);

        // Get title element
        TextView titleTextView = (TextView) rowView.findViewById(R.id.recipe_list_title);

        // Get subtitle element
        TextView subtitleTextView = (TextView) rowView.findViewById(R.id.recipe_list_subtitle);

        // Get detail element
        //TextView detailTextView = (TextView) rowView.findViewById(R.id.recipe_list_detail);

        // Get detail element
        TextView gradeTextView = (TextView) rowView.findViewById(R.id.recipe_list_grade);

        Problem recipe = (Problem) getItem(position);


        titleTextView.setText(recipe.name);
        subtitleTextView.setText(recipe.uploader);
        gradeTextView.setText(recipe.grade);


        //Picasso.with(mContext).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);


        return rowView;
    }
}

