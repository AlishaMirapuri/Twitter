package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.restclienttemplate.models.Tweet;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    Tweet tweet;

    @BindView(R.id.tvUsername) TextView tvUsername;
    @BindView(R.id.ivProfilePic) ImageView ivProfilePic;
    //@BindView(R.id.ivMedia) ImageView ivMedia;
    //@BindView(R.id.timeStamp) TextView timeStamp;
    @BindView(R.id.tvTweet) TextView tvTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("Detailed"));
        tvUsername.setText(tweet.user.name);
        tvTweet.setText(tweet.body);
        //timeStamp.setText(tweet.createdAt);
        Glide.with(this).load(tweet.user.profileImageUrl).into(ivProfilePic);

    }
}
