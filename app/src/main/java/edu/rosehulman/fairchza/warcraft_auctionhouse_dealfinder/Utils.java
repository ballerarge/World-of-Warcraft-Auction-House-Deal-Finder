package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;


import android.content.Context;
import android.util.Log;
import android.util.Xml;

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

    public static List<WoWItem> getWoWItem(Context context) {
        List<WoWItem> items = new ArrayList<>();

        InputStream is = null;
        try {
            is = new URL("http://www.wowhead.com/item=25225&xml").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            items = XMLParser.parse(is);
        } catch (XmlPullParserException e){
            Log.e("EEE", e.toString());
        } catch (IOException e) {
            Log.e("EEE", e.toString());
            e.printStackTrace();
        }
        return items;
    }

    public static class XMLParser {
        private static final String ns = null;

        public List<WowItem> parse(InputStream in) throws XmlPullParserException, IOException {
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

        private List<WowItem> readXml (XmlPullParser parser) throws XmlPullParserException, IOException {
            List<WowItem> entries = new ArrayList<>();

            Log.d("TTT", "Start of readXML");

            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();

                // Starts looking for the entry tag
                if (name.equals("wowhead")) {
                    entries.add(readState(parser));
                } else {
                    skip(parser);
                }
            }
            return entries;
        }

        private WoWItem readState(XmlPullParser parser) throws XmlPullParserException, IOException {
            parser.require(XmlPullParser.START_TAG, ns, "wowhead");
            String itemId = parser.getAttributeValue(null, "id");
            // Other string stuff in here, along with assigning the results
            // to values and stuff.
            // Here is where the WoWItem will be created, and it will be
            // returned at the end of the method.

            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                String name = parser.getName();
                if (name.equals("point")) {
                    String latString = parser.getAttributeValue(null, "lat");
                    parser.nextTag();
                    String lngString = parser.getAttributeValue(null, "lng");

                    double lat;
                    try {
                        lat = Double.parseDouble(latString);
                    } catch (Exception e) {
                        lat = 0.0;
                    }
                    double lng;
                    try {
                        lng = Double.parseDouble(lngString);
                    } catch (Exception e) {
                        lng = 0.0;
                    }
                    // WoWItem.addStuff(new Stuff with above)
                } else {
                    skip(parser);
                }
            }
            // Return WoWItem
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

        private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
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
