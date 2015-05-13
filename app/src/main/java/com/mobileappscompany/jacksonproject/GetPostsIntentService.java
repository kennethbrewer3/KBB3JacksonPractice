package com.mobileappscompany.jacksonproject;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetPostsIntentService extends IntentService {
    private static final String TAG = "GetPostsIntentService";

    public static final String BUNDLE_RESULT_POST_LIST = "com.mobileappscompany.POSTS_LIST";
    public static final int BUNDLE_SUCCESS_RESULT_CODE = 1000;
    public static final int BUNDLE_ERROR_RESULT_CODE = 2000;

    private static final String URL_STRING = "http://jsonplaceholder.typicode.com/posts";

    public static final String INTENT_KEY_RECEIVER = "com.mobileappscompany.BUNDLE_KEY_RECEIVER";

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    public static final String ACTION_GET_POSTS = "jacksonproject.action.GET_POSTS";
    private static final String ACTION_BAZ = "com.mobileappscompany.jacksonproject.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.mobileappscompany.jacksonproject.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.mobileappscompany.jacksonproject.extra.PARAM2";

    private ObjectMapper mapper;
    private List<Post> posts;
    ResultReceiver receiver;

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, GetPostsIntentService.class);
        intent.setAction(ACTION_GET_POSTS);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, GetPostsIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public GetPostsIntentService() {
        super("GetPostsIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Service is started");
        receiver = intent.getParcelableExtra(INTENT_KEY_RECEIVER);
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_POSTS.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        mapper = new ObjectMapper();

        try {
            posts = mapper.readValue(new URL(URL_STRING), new TypeReference<List<Post>>(){});
            ArrayList<Post> arrayListPosts = new ArrayList(posts);
            Bundle resultBundle = new Bundle();
            Log.d(TAG, "Size of posts list: " + posts.size());

            resultBundle.putParcelableArrayList(BUNDLE_RESULT_POST_LIST, arrayListPosts);
            receiver.send(BUNDLE_SUCCESS_RESULT_CODE,resultBundle);
        } catch (IOException e) {
            Log.e(TAG, e.toString());
            receiver.send(BUNDLE_ERROR_RESULT_CODE,null);
        }
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
