package com.osahonojo.glideandwebviewminiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.view.View;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String KEY_URLS = "list_urls";
    String KEY_DUMMY = "list_dummy";
    ImageView imageOne, imageTwo, imageThree, imageFour, imageFive;
    SharedPreferences sharedPreferences;
    ArrayList<String> urls;
    ArrayList<String> dummyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageOne = findViewById(R.id.imageOne);
        imageTwo = findViewById(R.id.imageTwo);
        imageThree = findViewById(R.id.imageThree);
        imageFour = findViewById(R.id.imageFour);
        imageFive = findViewById(R.id.imageFive);

        imageOne.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view){
                AlertDialog.Builder popup = new AlertDialog.Builder(MainActivity.this);
                popup.setTitle("Image URL");
                popup.setMessage(urls.get(0));
                popup.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                popup.setPositiveButton("Visit Website", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                });
                popup.create().show();
                return true;
                    // onLongClick() return boolean based on
                    // whether or not the callback consumed the long click
            }
        });

        imageTwo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder popup = new AlertDialog.Builder(MainActivity.this);
                popup.setTitle("Image URL");
                popup.setMessage(urls.get(1));
                popup.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                popup.setPositiveButton("Visit Website", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                });
                popup.create().show();
                return true;
            }
        });

        imageThree.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder popup = new AlertDialog.Builder(MainActivity.this);
                popup.setTitle("Image URL");
                popup.setMessage(urls.get(2));
                popup.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                popup.setPositiveButton("Visit Website", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                });
                popup.create().show();
                return true;
            }
        });

        imageFour.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder popup = new AlertDialog.Builder(MainActivity.this);
                popup.setTitle("Image URL");
                popup.setMessage(urls.get(3));
                popup.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                popup.setPositiveButton("Visit Website", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                });
                popup.create().show();
                return true;
            }
        });

        imageFive.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder popup = new AlertDialog.Builder(MainActivity.this);
                popup.setTitle("Image URL");
                popup.setMessage(urls.get(4));
                popup.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                popup.setPositiveButton("Visit Website", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, WebActivity.class);
                        MainActivity.this.startActivity(intent);
                    }
                });
                popup.create().show();
                return true;
            }
        });


        sharedPreferences = MainActivity.this.getSharedPreferences("alternate_db", Context.MODE_PRIVATE);
        loadArrayListsFromPreferences();


        // .with(this) didn't work for some reason

        Glide.with(MainActivity.this)
                .asBitmap()
                .load(urls.get(0))
                .into(imageOne);

        Glide.with(MainActivity.this)
                .asBitmap()
                .load(urls.get(1))
                .into(imageTwo);

        Glide.with(MainActivity.this)
                .asBitmap()
                .load(urls.get(2))
                .into(imageThree);

        Glide.with(MainActivity.this)
                .asBitmap()
                .load(urls.get(3))
                .into(imageFour);

        Glide.with(MainActivity.this)
                .asBitmap()
                .load(urls.get(4))
                .into(imageFive);
    }

    private void loadArrayListsFromPreferences() {
        ArrayList<String> startingURLs = new ArrayList<>(
                Arrays.asList("https://images.pexels.com/photos/346529/pexels-photo-346529.jpeg",
                        "https://images.pexels.com/photos/747964/pexels-photo-747964.jpeg",
                        "https://images.pexels.com/photos/2246476/pexels-photo-2246476.jpeg",
                        "https://images.pexels.com/photos/2440021/pexels-photo-2440021.jpeg",
                        "https://images.pexels.com/photos/847402/pexels-photo-847402.jpeg"));
        
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // if no preference value was found, create one and save it
        if (getUrls() == null) {
            editor.putString(KEY_URLS, gson.toJson(startingURLs));
            editor.apply();
            urls = startingURLs;
        }
        else {
            urls = getUrls();
        }

        if (getDummyList() == null) {
            editor.putString(KEY_DUMMY, gson.toJson(startingURLs));
            editor.apply();
            dummyList = startingURLs;
        }
        else {
            dummyList = getDummyList();
        }

    }

    private ArrayList<String> getUrls() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList<String> urls = gson.fromJson(sharedPreferences.getString(KEY_URLS, null), type);
        return urls;
    }

    private ArrayList<String> getDummyList() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>(){}.getType();
        ArrayList urls = gson.fromJson(sharedPreferences.getString(KEY_DUMMY, null), type);
        return urls;
    }
}