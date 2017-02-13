package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.os.AsyncTask;
import android.os.Parcelable;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by fairchza on 2/12/2017.
 */

public class JsonAuctionData extends AsyncTask<String, Object, ArrayList<AuctionItem>> {

    @Override
    protected ArrayList<AuctionItem> doInBackground(String... strings) {
        ArrayList<AuctionItem> items = new ArrayList<AuctionItem>();
        Document doc = null;
        try {
            doc = Jsoup.connect(strings[0]).ignoreContentType(true).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (doc != null) {
            Element body = doc.body();
            String json = body.text();
            ArrayList<String> jsons = new ArrayList<>();
            int breaker = 0;
            int a = 0;
            int b = 0;
            Integer numSplits = (json.length()-3000)/3000;
            Log.d("NUMBER OF SPLITS NEEDED", Integer.toString(numSplits));
            String opener = json.substring(0,214);
            for(int i=0; i <200; i++){
                if(breaker==0){
                    a = 3000;
                    b = 6000;
                } else {
                    a = breaker + (3000 * i);
                    b = a + 3000;
                }
                String json2 = json.substring(a,b);
                json2 = json2.substring(json2.indexOf(", {\"auc")+9);
                breaker = json2.lastIndexOf(", {\"auc");
                Log.d("Breaker is at", Integer.toString(breaker + (3000 * i)));
                String json3 = json2.substring(0, breaker-13);
                jsons.add(opener + json3 + "}" + "]" + "}");
                Log.d("LENGTH",Integer.toString(jsons.get(i).length()));
                Log.d("STRING", jsons.get(i));
            }

            Log.d("String length",Integer.toString(json.length()));
            String json2 = json.substring(3001, 7293);
            json = json.substring(0,3000);
            json = json + "]" + "}";
//            json2 = opener + json2 + "]" +"}";
            Log.d("JsonAuctionData", json);
//            JsonRealmAndAuctions info = null;
            AuctionItem item = null;
            ObjectMapper mapper = new ObjectMapper();

            try {
//                json = '(' + json + ')';
                JsonRealmAndAuctions info = mapper.readValue(json, JsonRealmAndAuctions.class);
                Log.d("NODE INFO",info.getAuctions().get(0).toString());
                ObjectMapper mapper1 = new ObjectMapper();
                    for(int i=0; i < info.getAuctions().size(); i++) {
                       item = mapper1.convertValue(info.getAuctions().get(i),AuctionItem.class);
                        items.add(item);
                        Log.d("ITEMNUMBER", item.getItem().toString());
                    }
            } catch (IOException e) {
                Log.d("EEE", "ERROR:" + e.toString());
            }
            for(int i=0; i < 200; i++) {
                try {
//                json = '(' + json + ')';
                    JsonRealmAndAuctions info = mapper.readValue(jsons.get(i), JsonRealmAndAuctions.class);
                    Log.d("NODE INFO", info.getAuctions().get(0).toString());
                    ObjectMapper mapper1 = new ObjectMapper();
                    for (int j = 0; j < info.getAuctions().size(); j++) {
                        item = mapper1.convertValue(info.getAuctions().get(j), AuctionItem.class);
                        if(item!=null) {

                            items.add(item);
//                            Log.d("ITEMNUMBER", item.getItem().toString());
                        }
                    }
                } catch (IOException e) {
                    Log.d("EEE", "ERROR:" + e.toString());
                }
            }
            return items;
        }
        Log.d("LUL","SOMEHOW DOC IS NULL?");
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<AuctionItem> auctionItems) {
        if(auctionItems !=null) {
            MainActivity.myAuctions = auctionItems;
        }
        Log.d("AT THE END THERE ARE",Integer.toString(MainActivity.myAuctions.size()));
    }
}
