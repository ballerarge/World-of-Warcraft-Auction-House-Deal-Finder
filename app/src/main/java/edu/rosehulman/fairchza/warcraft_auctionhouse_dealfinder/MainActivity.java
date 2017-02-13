package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<WowItem> myItems;
    public static ArrayList<AuctionItem> myAuctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myAuctions = new ArrayList<AuctionItem>();
        new JsonAuctionURL().execute();
        myItems = new ArrayList<WowItem>();
        myItems.add(0, new WowItem("Sulfuras, Hand of Ragnaros", "80", "160402", "5", "60"));
        myItems.add(0, new WowItem("Major Mana Potion", "59", "6000", "1", "49"));

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            MainFragment fragment = MainFragment.newInstance(myItems);
            ft.add(R.id.fragment_container, fragment);
            ft.commit();
        }
    }
    public void setMyAuctions(ArrayList<AuctionItem> items){
        this.myAuctions = items;
    }
}
