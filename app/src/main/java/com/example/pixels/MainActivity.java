package com.example.pixels;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.loader.content.Loader;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeRefresh;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.consumer.SlidingConsumer;
import com.billy.android.swipe.consumer.StretchConsumer;
import com.billy.android.swipe.listener.SwipeListener;
import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.LogInCallback;
import com.parse.LogOutCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener,View.OnClickListener{

    Button lol;
    TextView view1;
    EditText username,password;
    ImageView viewz,viewm;

    ImageView add,search,profile;

    TextView alreadyhaveaccount,greyline;
    LinearLayout layout,layout1;
    ScrollView scrollView,scrollView1;

    Typeface typeface;

    static int i=0;
    public void userfeed() {
        scrollView.setVisibility(View.VISIBLE);
        layout.setVisibility(View.VISIBLE);
        scrollView1.setVisibility(View.VISIBLE);
        layout1.setVisibility(View.VISIBLE);

        layout.removeAllViews();
        layout1.removeAllViews();

        username.setVisibility(View.INVISIBLE);
        password.setVisibility(View.INVISIBLE);
        lol.setVisibility(View.INVISIBLE);
        alreadyhaveaccount.setVisibility(View.INVISIBLE);
        viewz.setVisibility(View.INVISIBLE);
        viewm.setVisibility(View.INVISIBLE);

        add.setVisibility(View.VISIBLE);
        profile.setVisibility(View.VISIBLE);
        search.setVisibility(View.VISIBLE);

        greyline.setVisibility(View.VISIBLE);
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        Log.i("value of i ", String.valueOf(i));
        query.findInBackground(new FindCallback<ParseUser>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    if(objects.size()>0) {
                        Log.i("size", String.valueOf(objects.size()));
                        // Log.i("current profile", user.getUsername());
                        Log.i("user", ParseUser.getCurrentUser().getUsername());
                        ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Image");
                        query1.orderByDescending("createdAt");
                        query1.findInBackground(new FindCallback<ParseObject>() {
                            @SuppressLint("ResourceType")
                            @Override
                            public void done(List<ParseObject> objects, ParseException e) {
                                if (e == null) {
                                    for (ParseObject object : objects) {

                                        Log.i("user", object.getString("username"));
                                        Log.i("user", String.valueOf(object.getParseFile("image")));
                                        final ParseFile file = (ParseFile) object.get("image");
                                        Log.i("url",file.getUrl());
                                        ImageView image = new ImageView(MainActivity.this);
                                        ImageView image1 = new ImageView(MainActivity.this);
                                        TextView name = new TextView(MainActivity.this);
                                        name.setText(" "+object.getString("username"));
                                        name.setPadding(40,15,10,15);
                                        name.setTextAppearance(MainActivity.this,R.style.fontfam);

                                        TextView line = new TextView(MainActivity.this);
                                        line.setBackgroundColor(Color.GRAY);
                                        line.setTextSize(1);


                                        TextView caption = new TextView(MainActivity.this);
                                        caption.setText(object.getString("caption"));
                                        caption.setPadding(20,10,10,30);
                                        caption.setTextAppearance(MainActivity.this,R.style.capfam);

                                        Picasso.with(MainActivity.this).load(R.drawable.lol)
                                                .resize(80,80)
                                                .into(image1);


                                        Picasso.with(MainActivity.this).load(file.getUrl()).
                                                fit().centerInside()
                                                .noFade().
                                                placeholder(R.drawable.holder).
                                                into(image);

                                        layout.addView(line);
                                        layout.addView(name);
                                        layout.addView(image);
                                        layout.addView(caption);
                                        //layout1.addView(image1);
                                        //layout1.addView(image);

                                    }
                                         /*   if (object.getString("username").equals(user.getUsername()))
                                           {
                                                //Log.i("username",object.getString("username"));
                                               // Log.i("username",user.getString("username"));
                                                Log.i("images", String.valueOf(object.getParseFile("image")));
                                                final ParseFile file = (ParseFile) object.get("image");
                                                Log.i("url",file.getUrl());
                                              /*  file.getDataInBackground(new GetDataCallback() {
                                            @Override
                                            public void done(byte[] data, ParseException e) {
                                                if(e==null&&data!=null)
                                                {
                                                    Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                                                    ImageView image = new ImageView(MainActivity.this);
                                                    image.setLayoutParams(new ViewGroup.LayoutParams(
                                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                                            ViewGroup.LayoutParams.WRAP_CONTENT
                                                    ));

                                                    image.setImageBitmap(bitmap);
                                                    /*TextView viewlol = new TextView(MainActivity.this);
                                                    viewlol.setLayoutParams(new ViewGroup.LayoutParams(
                                                            ViewGroup.LayoutParams.MATCH_PARENT,
                                                            ViewGroup.LayoutParams.MATCH_PARENT
                                                            ));
                                                    viewlol.setText("loolol");
                                                    ImageView image = new ImageView(MainActivity.this);
                                               /*ImageView image1 = new ImageView(MainActivity.this);
                                               //encode image to base64 string
                                               ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                               Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
                                               bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                               byte[] imageBytes = baos.toByteArray();
                                               String imageString = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);

                                               //decode base64 string to image
                                               imageBytes = android.util.Base64.decode(imageString, android.util.Base64.DEFAULT);
                                               Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                                               image1.setImageBitmap(decodedImage);

                                                    Picasso.with(MainActivity.this).load(file.getUrl()).
                                                            fit().centerInside()
                                                            .noFade().
                                                            placeholder(R.drawable.holder).
                                                            into(image);
                                                    layout.addView(image);
                                                    //layout.addView(viewlol);
                                           }
                                }
                            }
                                else Log.i("error", Objects.requireNonNull(e.getMessage()));
                            }
                        });


                            name.setText(user.getUsername()+"'s feed");
                            if(i+1==objects.size())
                            {
                                i=0;
                            }
                            else i++;

                    }
                    else if(i<objects.size()&& user.getUsername().equals(ParseUser.getCurrentUser().getUsername()))
                    {
                        i++;
                        userfeed();
                    }*/
                                } else Log.i("user", Objects.requireNonNull(e.getMessage()));
                            }
                        });

                    }
                }
            }
        });
    }

    public void add(View view){
        permissions();
    }

    public void profile(View view)
    {
        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }


    public void getphoto()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);
    }


    public void permissions()
    {
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.
                    this,Manifest.permission.READ_EXTERNAL_STORAGE)){
                new AlertDialog.Builder(MainActivity.this).setTitle("Location access required!").
                        setMessage("please give permission").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
            else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]
                        {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        }
        else
            getphoto();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK&&data!=null)
        {
            Uri image = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),image);
                Log.i("photo saved","done");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byte[] array = stream.toByteArray();
                ParseFile file = new ParseFile("image.jpeg",array);
                ParseObject object = new ParseObject("Image");
                object.put("image",file);
                object.put("username",ParseUser.getCurrentUser().getUsername());
                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                        {
                            Toast.makeText(MainActivity.this, "Image saved successfully!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(MainActivity.this,e.getMessage() +"\nPlease try again later", Toast.LENGTH_SHORT).show();
                    }
                });


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public void loginscreen()
    {

        i=0;
        scrollView.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.INVISIBLE);

        scrollView1.setVisibility(View.INVISIBLE);
        layout1.setVisibility(View.INVISIBLE);

        username.setVisibility(View.VISIBLE);
        password.setVisibility(View.VISIBLE);
        lol.setVisibility(View.VISIBLE);
        alreadyhaveaccount.setVisibility(View.VISIBLE);
        viewz.setVisibility(View.VISIBLE);
        viewm.setVisibility(View.VISIBLE);

        //name.setVisibility(View.INVISIBLE);
        greyline.setVisibility(View.INVISIBLE);

        add.setVisibility(View.INVISIBLE);
        profile.setVisibility(View.INVISIBLE);
        search.setVisibility(View.INVISIBLE);
    }

     /* public void mainscreen()
    {
        names.clear();
        username.setVisibility(View.INVISIBLE);
        password.setVisibility(View.INVISIBLE);
        lol.setVisibility(View.INVISIBLE);
        view1.setVisibility(View.INVISIBLE);
        viewz.setVisibility(View.INVISIBLE);
        viewm.setVisibility(View.INVISIBLE);
        view.setVisibility(View.VISIBLE);
        name.setVisibility(View.VISIBLE);
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null) {
                    if (objects.size() > 0) {
                        for (ParseUser user : objects) {
                            Log.i("objects", user.getString("username"));
                            names.add(user.getString("username"));
                            adapter.notifyDataSetChanged();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }
                else Log.i("failed", Objects.requireNonNull(e.getMessage()));
            }
        });
    }*/

    @SuppressLint("SetTextI18n")
    public void exists (View view)
    {
        lol.setText("login");
        alreadyhaveaccount.setVisibility(View.INVISIBLE);
    }

    public void signup(View view)
    {
        if (lol.getText() == "signup") {
            if (username.getText().toString().matches("") || password.getText().toString().matches("")) {
                Toast.makeText(MainActivity.this, "Please enter valid details!", Toast.LENGTH_SHORT).show();
            } else {
                ParseUser user = new ParseUser();
                user.setUsername(username.getText().toString());
                Log.i("username", username.getText().toString());
                user.setPassword(password.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("Sign Up Successfull", "done");
                            Toast.makeText(MainActivity.this, "Sign up Successfull!", Toast.LENGTH_SHORT).show();
                            userfeed();
                        } else {
                            Log.i("failed", "f");
                            Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } else {
            ParseUser.logInInBackground(username.getText().toString(),password.getText().toString(),    new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        Log.i("Login", "done");
                        Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_SHORT).show();
                        userfeed();
                    } else {
                        Log.i("Login", "failed" + e.toString());
                        Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    @SuppressLint({"ClickableViewAccessibility", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Start","f");

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("As6cEnuWUtpQdhjkfOE4mRy0bOpE0Aan3i7Na7y2")
                .clientKey("ik7M2G6FM7QZMCMZmyz42sO2ZQ7lPO2m4haDDz99")
                .server("https://parseapi.back4app.com/")
                .build()
        );

        i=0;

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        lol = findViewById(R.id.login);
        alreadyhaveaccount = findViewById(R.id.alreadyhaveaccount);
        lol.setText("signup");
        viewz=findViewById(R.id.instagram);
        viewm=findViewById(R.id.instalogo);
        layout = findViewById(R.id.layout);
        layout1 = findViewById(R.id.layout1);

        //name = findViewById(R.id.name);
        greyline = findViewById(R.id.greyline);
        scrollView = findViewById(R.id.scrollview);
        scrollView1 = findViewById(R.id.scrollview1);

        add = findViewById(R.id.add);
        profile = findViewById(R.id.profile);
        search = findViewById(R.id.search);



        /*SmartSwipe.wrap(name).addConsumer(new StretchConsumer()).enableTop().addListener(new SwipeListener() {
            @Override
            public void onConsumerAttachedToWrapper(SmartSwipeWrapper wrapper, SwipeConsumer consumer) {

            }

            @Override
            public void onConsumerDetachedFromWrapper(SmartSwipeWrapper wrapper, SwipeConsumer consumer) {

            }

            @Override
            public void onSwipeStateChanged(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int state, int direction, float progress) {

            }

            @Override
            public void onSwipeStart(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {

            }

            @Override
            public void onSwipeProcess(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction, boolean settling, float progress) {
            }

            @Override
            public void onSwipeRelease(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction, float progress, float xVelocity, float yVelocity) {

            }

            @Override
            public void onSwipeOpened(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
                i--;
                userfeed();
            }

            @Override
            public void onSwipeClosed(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {

            }
        });

      /* SmartSwipe.wrap(layout).addConsumer(new SwipeConsumer() {
            @Override
            protected void onDisplayDistanceChanged(int distanceXToDisplay, int distanceYToDisplay, int dx, int dy) {
                userfeed();
            }
        }).enableRight();*/

       SmartSwipeRefresh.SmartSwipeRefreshDataLoader loader =new SmartSwipeRefresh.SmartSwipeRefreshDataLoader() {
            @Override
            public void onRefresh(SmartSwipeRefresh ssr) {
                i--;
                userfeed();
                boolean loaded =false;
                ssr.setNoMoreData(loaded);
                ssr.finished(true);
            }
            @Override
            public void onLoadMore(SmartSwipeRefresh ssr) {

            }
        };

        //SmartSwipeRefresh.drawerMode(layout,false).setDataLoader(loader).disableLoadMore();
        SmartSwipeRefresh.drawerMode(scrollView,false).setDataLoader(loader).disableLoadMore();


        //feed = new ArrayList<>();
        //  adapter1 = new ArrayAdapter<>(this,android.R.layout.simple_gallery_item);
        //layout.addView(image);*/
       /* view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

       SmartSwipe.wrap(layout)
                .addConsumer(new StayConsumer()) //contentView stay while swiping with StayConsumer
                .enableAllDirections() //enable directions as needed
                .addListener(new SimpleSwipeListener() {
                    @Override
                    public void onSwipeOpened(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
                        //2:right
                            //  4: top
                            //  8: bottom
                    }
                });
       SmartSwipeRefresh.SmartSwipeRefreshDataLoader loader = new SmartSwipeRefresh.SmartSwipeRefreshDataLoader() {
           @Override
           public void onRefresh(SmartSwipeRefresh ssr) {
               i--;
               userfeed();
           }

           @Override
           public void onLoadMore(SmartSwipeRefresh ssr) {

           }
       };

        SmartSwipeRefresh.drawerMode(layout, false).setDataLoader(loader);
        SmartSwipeRefresh.behindMode(layout, false).setDataLoader(loader);
        SmartSwipeRefresh.scaleMode(layout, false).setDataLoader(loader);
        SmartSwipeRefresh.translateMode(layout, false).setDataLoader(loader);

*/
        /*view.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeTop() {
                Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
                getphoto();
            }
            public void onSwipeRight() {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
                userfeed();
            }
            public void onSwipeBottom() {
                Toast.makeText(MainActivity.this, "Refreshing!", Toast.LENGTH_SHORT).show();
                i--;
                userfeed();
            }

        });*/

        if(ParseUser.getCurrentUser()!=null)
        {
            userfeed();
        }
        else{
            loginscreen();
        }


        password.setOnKeyListener(this);
        viewz.setOnClickListener(this);

       /* ParseUser.logOut();
      if(ParseUser.getCurrentUser()!=null)
      {
          Log.i("CurrentUser","user logged in" + ParseUser.getCurrentUser().getUsername());

      }else
          Log.i("current user","user not logged in ");*/

        /*  ParseUser.logInInBackground("Aditya Agarwal","hoho", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user!=null)
                {
                    Log.i("Login","done");

                }else
                    Log.i("LOgin","failed" + e.toString());
            }
        });*/

        /*ParseUser user = new ParseUser();
        user.setUsername("Aditya Agarwal");
        user.setPassword("hohoho");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null)
                {
                    Log.i("sucessfull","done");
                }
                else Log.i("failed","f");
            }
        });*/
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_ENTER&& event.getAction()==KeyEvent.ACTION_DOWN)
        {
            signup(v);   ///impppp itll be invoked twice otherwise both when key is pressed and released
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View focusedView =getCurrentFocus();
        /*
         * If no view is focused, an NPE will be thrown
         *
         * Maxim Dmitriev
         */
        if (focusedView != null) {
            assert manager != null;
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getphoto();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        //inflater.inflate(R.menu.);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Logout:
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null)
                        {
                            Log.i("logged out","out");
                            Toast.makeText(getApplicationContext(),"Logged out!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                lol.setText("signup");
                loginscreen();
                break;

            case R.id.share:
                permissions();
        }
        return super.onOptionsItemSelected(item);
    }
}


 /* public void login(View view)
    {
        ParseUser.logInInBackground(String.valueOf(username), String.valueOf(password), new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user!=null)
                {
                    Log.i("Login","done");
                    Toast.makeText(getApplicationContext(),"Login successful!",Toast.LENGTH_SHORT).show();

                }else
                    Log.i("Login","failed" + e.toString());
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }*/


