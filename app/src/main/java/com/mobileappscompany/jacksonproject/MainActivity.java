package com.mobileappscompany.jacksonproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";

    private ResultReceiver receiver;

    private ListView postListView;
    private List<Post> posts;
    private PostArrayAdapter postArrayAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent intent = new Intent(this,GetPostsIntentService.class);
        intent.setAction(GetPostsIntentService.ACTION_GET_POSTS);
        intent.putExtra(GetPostsIntentService.INTENT_KEY_RECEIVER, receiver);
        startService(intent);

        context = this;
        postListView = (ListView)findViewById(R.id.postListView);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public MainActivity() {
        receiver = new ResultReceiver(new Handler()) {
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if(resultCode == GetPostsIntentService.BUNDLE_SUCCESS_RESULT_CODE) {
                    posts = resultData.getParcelableArrayList(GetPostsIntentService.BUNDLE_RESULT_POST_LIST);
                    ArrayList<Post> arrayListPost = new ArrayList(posts);
                    postArrayAdapter = new PostArrayAdapter(context,android.R.layout.simple_list_item_1,arrayListPost);
                    postListView.setAdapter(postArrayAdapter);
                    Log.d(TAG, "Size of posts: " + posts.size());
                }

                if(resultCode == GetPostsIntentService.BUNDLE_ERROR_RESULT_CODE) {
                    Log.e(TAG, "Error");
                }
            }
        };
    }
}
