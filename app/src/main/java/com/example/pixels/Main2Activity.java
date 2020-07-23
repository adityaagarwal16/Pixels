package com.example.pixels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final TextView title = findViewById(R.id.title);
        final LinearLayout layoutfeed = findViewById(R.id.layoutfeed);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    if(objects.size()>0) {
                        Log.i("user", ParseUser.getCurrentUser().getUsername());
                        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Image");
                        query1.orderByDescending("createdAt");
                        query1.findInBackground(new FindCallback<ParseObject>() {
                            @SuppressLint("ResourceType")
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if (e == null) {
                                    for(ParseObject object : objects) {
                                        if (object.getString("username").equals(ParseUser.getCurrentUser().getUsername())) {
                                            title.setText(ParseUser.getCurrentUser().getUsername()+"'s feed");
                                            setTitle(ParseUser.getCurrentUser().getUsername());
                                            Log.i("user", String.valueOf(object.getParseFile("image")));
                                            final ParseFile file = (ParseFile) object.get("image");
                                            Log.i("url", file.getUrl());
                                            ImageView image = new ImageView(Main2Activity.this);
                                            ImageView image1 = new ImageView(Main2Activity.this);

                                            TextView line = new TextView(Main2Activity.this);
                                            line.setBackgroundColor(Color.GRAY);
                                            line.setTextSize(1);

                                            TextView caption = new TextView(Main2Activity.this);
                                            caption.setText(object.getString("caption"));
                                            caption.setPadding(20, 10, 10, 30);
                                            caption.setTextAppearance(Main2Activity.this, R.style.capfam);

                                            Picasso.with(Main2Activity.this).load(R.drawable.lol)
                                                    .resize(80, 80)
                                                    .into(image1);


                                            Picasso.with(Main2Activity.this).load(file.getUrl())
                                                    .fit().centerInside()
                                                    .noFade().
                                                    placeholder(R.drawable.holder)
                                                    .into(image);

                                            layoutfeed.addView(line);
                                            layoutfeed.addView(image);
                                            layoutfeed.addView(caption);
                                            //layout1.addView(image1);
                                            //layout1.addView(image);

                                        }
                                    }
                                } else Log.i("user", Objects.requireNonNull(e.getMessage()));
                            }
                        });

                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        //inflater.inflate(R.menu.);
        return super.onCreateOptionsMenu(menu);
    }


}

