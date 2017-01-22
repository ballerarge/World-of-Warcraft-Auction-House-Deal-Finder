package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;


import android.os.AsyncTask;
import android.util.Log;
import android.util.Xml;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by decramrj on 1/16/2017.
 */

public class Utils {

    public static class XMLTask extends AsyncTask<String, Void, List<WowItem>> {

        @Override
        protected List<WowItem> doInBackground(String... urls) {
            List<WowItem> items = new ArrayList<>();

            InputStream is = null;
            try {
                is = new URL(urls[0]).openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                items.add(XMLParser.parse(is));
            } catch (XmlPullParserException e){
                Log.e("EEE", e.toString());
            } catch (IOException e) {
                Log.e("EEE", e.toString());
                e.printStackTrace();
            }
            return items;
        }

        protected void onPostExecute(List<WowItem> itemList) {
            System.out.println("Name of item: " + itemList.get(0).getName());
            System.out.println("Subclass of item: " + itemList.get(0).getSubclass());
            System.out.println("Quality of item: " + itemList.get(0).getQuality());
            System.out.println("Id of item: " + itemList.get(0).getId());
            System.out.println("Icon of item: " + itemList.get(0).getIcon());
            System.out.println("Level of item: " + itemList.get(0).getItemLevel());
            System.out.println("Item has sockets? " + itemList.get(0).isHasSockets());
            System.out.println("Required level of item: " + itemList.get(0).getRequiredLevel());
        }
    }

    public static class XMLParser {
        private static final String ns = null;

        public static WowItem parse(InputStream in) throws XmlPullParserException, IOException {
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                parser.nextTag();
                return readXml(parser);
            } finally {
                in.close();
            }
        }

        private static WowItem readXml(XmlPullParser parser) throws XmlPullParserException, IOException {
            WowItem entry = new WowItem();

            Log.d("TTT", "Start of readXML");
            entry = readState(parser);
//            while (parser.next() != XmlPullParser.END_TAG) {
//                if (parser.getEventType() != XmlPullParser.START_TAG) {
//                    continue;
//                }
//                String name = parser.getName();
//
//                // Starts looking for the entry tag
//                if (name.equals("wowhead")) {
//                    entries.add(readState(parser));
//                } else {
//                    skip(parser);
//                }
//            }
            return entry;
        }

        private static WowItem readState(XmlPullParser parser) throws XmlPullParserException, IOException {
            //parser.require(XmlPullParser.START_TAG, ns, "wowhead");
            //String itemId = parser.getAttributeValue(null, "id");
            //Log.d("TTT", "Item id: " + itemId);
            WowItem item = new WowItem();
            while (parser.next() != XmlPullParser.END_DOCUMENT) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if (name.equals("item")) {
                    String id = parser.getAttributeValue(null, "id");
                    item.setId(id);
                    // WoWItem.addStuff(new Stuff with above)
                } else if (name.equals("level")) {
                    int level = Integer.parseInt(parser.nextText());
                    item.setItemLevel(level);
                } else if (name.equals("name")) {
                    String itemName = parser.nextText();
                    item.setName(itemName);
                } else if (name.equals("quality")) {
                    String quality = parser.nextText();
                    item.setQuality(quality);
                } else if (name.equals("icon")) {
                    String icon = parser.getAttributeValue(null, "displayId");
                    item.setIcon(icon);
                } else if (name.equals("subclass")) {
                    String subClass = parser.nextText();
                    item.setSubclass(subClass);
                } else if (name.equals("jsonEquip")) {
                    String json = parser.nextText();
                    json = "{" + json + "}";
                    System.out.println("The JSON is: " + json);

                    JsonInfo info = null;
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        info = mapper.readValue(json, JsonInfo.class);
                    } catch (IOException e) {
                        Log.d("EEE", "ERROR:" + e.toString());
                    }
                    if (info.getNsockets() != 0) {
                        item.setHasSockets(true);
                    } else {
                        item.setHasSockets(false);
                    }
                    item.setRequiredLevel(info.getReqlevel());
                } else {
                    skip(parser);
                }
            }
            // Return WoWItem
            return item;
        }

        // For the tag's title and summary, extracts their text values.
        // May not be used, but I'm not sure yet.
        private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
            String result = "";
            if (parser.next() == XmlPullParser.TEXT) {
                result = parser.getText();
                parser.nextTag();
            }
            return result;
        }

        private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                throw new IllegalStateException();
            }
            int depth = 1;
            while (depth != 0) {
                switch (parser.next()) {
                    case XmlPullParser.END_TAG:
                        depth--;
                        break;
                    case XmlPullParser.START_TAG:
                        depth++;
                        break;
                }
            }
        }
    }
}
