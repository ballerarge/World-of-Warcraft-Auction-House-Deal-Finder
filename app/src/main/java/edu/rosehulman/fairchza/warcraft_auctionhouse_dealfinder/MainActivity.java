package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ArrayList<WowItem> myItems;
    public static ArrayList<AuctionItem> myAuctions;
    private final static String PREFS = "PREFS";
    private static final String ITEMS = "items";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAuctions = new ArrayList<AuctionItem>();
        Timer time = new Timer();
        TimerTask updateData = new TimerTask() {
            @Override
            public void run() {
                new JsonAuctionURL().execute();
            }
        };
        time.schedule(updateData, 1000*60*60);
        new JsonAuctionURL().execute();

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String json = prefs.getString(ITEMS, "");

        if (json.equals("")) {
            myItems = new ArrayList<>();
        } else {
            Gson gson = new Gson();
            myItems = gson.fromJson(json, new TypeToken<List<WowItem>>()
            {
            }.getType());
        }


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MainFragment fragment = MainFragment.newInstance(myItems);
        ft.add(R.id.fragment_container, fragment);
        ft.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myItems);
        editor = prefs.edit();
        editor.remove(ITEMS).commit();
        editor.putString(ITEMS, json);
        editor.commit();
    }
    public void setMyAuctions(ArrayList<AuctionItem> items){
        this.myAuctions = items;
    }
}
