package com.mobileappscompany.jacksonproject;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Kenneth Brewer on 3/20/2015.
 */
public class PostListItem extends RelativeLayout {

    private final String TAG = "PostListItem";

    private TextView     textUserId;
    private TextView     textId;
    private TextView     textTitle;
    private TextView     textBody;

    private Context context;

    private Post post;

    public PostListItem(Context context) {
        this(context, null, 0);
    }

    public PostListItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PostListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.post_list_item, this);

        initControls();
    }

    private void initControls() {
        textUserId  = (TextView)findViewById(R.id.textUserId);
        textId      = (TextView)findViewById(R.id.textId);
        textTitle   = (TextView)findViewById(R.id.textTitle);
        textBody    = (TextView)findViewById(R.id.textBody);
    }

    private void populateItems() {
        textUserId.setText("User ID: " + Integer.toString(post.getUserId()));
        textId.setText("ID: " + Integer.toString(post.getId()));
        textTitle.setText("Title: " + post.getTitle());
        textBody.setText("Body: " + post.getBody());
    }

    public void setPost(Post post) {
        this.post = post;
        populateItems();
    }
}
