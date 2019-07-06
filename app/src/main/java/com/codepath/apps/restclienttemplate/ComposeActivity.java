package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {

    @BindView(R.id.etCompose) EditText etCompose;
    TwitterClient client;
    @BindView(R.id.mTextView) TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        client = TwitterApp.getRestClient(ComposeActivity.this);
//        etCompose = (EditText) findViewById(R.id.etCompose);
//        mTextView = (TextView) findViewById(R.id.mTextView);
        ButterKnife.bind(this);
        etCompose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTextView.setText("Characters left: " + String.valueOf(280 - s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void onSubmit(View v) {
        this.finish();
    }

    public void onSendClick(View view) {
        //etCompose = findViewById(R.id.etCompose);
        ButterKnife.bind(this);
        client.sendTweet(etCompose.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Tweet newTweet = null;
                try {
                    newTweet = Tweet.fromJSON(response);
                    Intent i = new Intent();
                    i.putExtra("tweet", Parcels.wrap(newTweet)); // pass arbitrary data to launched activity
                    setResult(RESULT_OK, i);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
