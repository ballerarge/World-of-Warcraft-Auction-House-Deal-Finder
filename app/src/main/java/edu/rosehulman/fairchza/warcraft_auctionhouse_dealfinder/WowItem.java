package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by decramrj on 1/19/2017.
 */

public class WowItem implements Parcelable {

    private String level;
    private String name_enus;
    private String priceavg;
    private String quality;
    private String requiredLevel;

    public WowItem() {
    }

    public WowItem(String name, String level, String price,
                   String quality, String requiredLevel) {
        this.level = level;
        this.name_enus = name;
        setPrice(price);
        setQuality(quality);
        this.requiredLevel = requiredLevel;
    }

    protected WowItem(Parcel in) {
        level = in.readString();
        name_enus = in.readString();
        priceavg = in.readString();
        quality = in.readString();
        requiredLevel = in.readString();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name_enus;
    }

    public void setName(String name) {
        this.name_enus = name;
    }

    public String getPrice() {
        return priceavg;
    }

    public void setPrice(String price) {
        String copper = "0";
        String silver = "0";
        String gold = "0";

        if (price.length() >= 1) {
            copper = getNum(price);
            price = price.substring(0, price.length() - 2);
        }

        if (price.length() >= 1) {
            silver = getNum(price);
            price = price.substring(0, price.length() - 2);
        }

        if (price.length() >= 1) {
            gold = getNum(price);
            price = price.substring(0, price.length() - 2);
        }

        if (copper.length() == 2 && copper.charAt(0) == '0') {
            copper = copper.substring(1);
        }

        if (silver.length() == 2 && silver.charAt(0) == '0') {
            silver = silver.substring(1);
        }

        if (gold.length() == 2 && gold.charAt(0) == '0') {
            gold = gold.substring(1);
        }

        this.priceavg = "Gold: " + gold + ", Silver: " + silver + ", Copper: " + copper;
    }

    public String getNum(String price) {
        if (price.length() <= 2) {
            return price;
        } else {
            return price.substring(price.length() - 2, price.length());
        }
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        if (quality.equals("0")) {
            this.quality = "Poor";
        } else if (quality.equals("1")) {
            this.quality = "Common";
        } else if (quality.equals("2")) {
            this.quality = "Uncommon";
        } else if (quality.equals("3")) {
            this.quality = "Rare";
        } else if (quality.equals("4")) {
            this.quality = "Epic";
        } else if (quality.equals("5")) {
            this.quality = "Legendary";
        } else {
            Log.d("EEE", "Quality of item not supported: " + quality);
            throw new UnsupportedOperationException();
        }
    }

    public String getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(String requiredLevel) {
        this.requiredLevel = requiredLevel;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(level);
        dest.writeString(name_enus);
        dest.writeString(priceavg);
        dest.writeString(quality);
        dest.writeString(requiredLevel);
    }

    public static final Creator<WowItem> CREATOR = new Creator<WowItem>() {
        @Override
        public WowItem createFromParcel(Parcel in) {
            return new WowItem(in);
        }

        @Override
        public WowItem[] newArray(int size) {
            return new WowItem[size];
        }
    };

    //    private String id; // Done
//    private String name;
//    private String icon; // Done, maybe
//    //private JSONObject itemSpells;
//    private int itemLevel; // Done
//    private String quality; // Done
//    private int requiredLevel; // Done
//    private boolean hasSockets; // Done
//    private String subclass; // Done
//
//    public WowItem () {
//        hasSockets = false;
//    }
//
//    public String getSubclass() {
//        return subclass;
//    }
//
//    public void setSubclass(String subclass) {
//        this.subclass = subclass;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
//
////    public JSONObject getItemSpells() {
////        return itemSpells;
////    }
////
////    public void setItemSpells(JSONObject itemSpells) {
////        this.itemSpells = itemSpells;
////    }
//
//    public int getItemLevel() {
//        return itemLevel;
//    }
//
//    public void setItemLevel(int itemLevel) {
//        this.itemLevel = itemLevel;
//    }
//
//    public String getQuality() {
//        return quality;
//    }
//
//    public void setQuality(String quality) {
//        this.quality = quality;
//    }
//
//    public int getRequiredLevel() {
//        return requiredLevel;
//    }
//
//    public void setRequiredLevel(int requiredLevel) {
//        this.requiredLevel = requiredLevel;
//    }
//
//    public boolean isHasSockets() {
//        return hasSockets;
//    }
//
//    public void setHasSockets(boolean hasSockets) {
//        this.hasSockets = hasSockets;
//    }

}
