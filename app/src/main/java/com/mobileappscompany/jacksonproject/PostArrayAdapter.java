package com.mobileappscompany.jacksonproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Android1 on 5/12/2015.
 */
public class PostArrayAdapter extends ArrayAdapter<Post> {
    private final String TAG = "PostArrayAdapter";
    private Context context;
    ArrayList<Post> posts;

    public PostArrayAdapter(Context context, int resource, ArrayList<Post> posts) {
        super(context,resource,posts);
        this.context = context;
        this.posts = posts;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post currentPost = posts.get(position);
        PostListItem row = new PostListItem(context);
        row.setPost(currentPost);
        return row;
    }
}
