package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by decramrj on 1/20/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonAuctionURL extends AsyncTask<String, String , String> {


    @Override
    protected String doInBackground(String... strings) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://us.api.battle.net/wow/auction/data/magtheridon?locale=en_US&apikey=74nby4tz5efkfqnttspk3af6343hjsxr").ignoreContentType(true).get();
        } catch (IOException e) {
            Log.d("LOADAUCTIONS",e.toString());
        }
        if(doc!=null) {
            Element body = doc.body();
            String json = body.text();
            json = json.substring(18, 119);
            Log.d("AuctionHouseDataJSON", json);
            return json;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
       new JsonAuctionData().execute(s);
    }
}
