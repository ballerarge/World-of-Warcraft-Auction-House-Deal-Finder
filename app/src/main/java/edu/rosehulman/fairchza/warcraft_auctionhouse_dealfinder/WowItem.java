package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by decramrj on 1/19/2017.
 */

public class WowItem implements Parcelable {

    private String level;
    private String name_enus;
    private String price;
    private String quality;
    private String requiredlevel;
    private String id;
    private String priceNum;
    public WowItem() {
    }

    public WowItem(String name, String level, String price,
                   String quality, String requiredlevel, String id) {
        this.level = level;
        this.name_enus = name;
        setPriceavg(price);
        setQuality(quality);
        this.requiredlevel = requiredlevel;
        this.id = id;
    }

    protected WowItem(Parcel in) {
        level = in.readString();
        name_enus = in.readString();
        price = in.readString();
        quality = in.readString();
        requiredlevel = in.readString();
        id = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName_enus() {
        return name_enus;
    }

    public void setName_enus(String name) {
        this.name_enus = name;
    }

    public String getPriceavg() {
        return price;
    }

    public void setPriceavg(String price) {
        this.priceNum = price;
        String copper = "0";
        String silver = "0";
        String gold = "0";

        if (price.length() >= 1) {
            silver = getNum(price);
            if (price.length() == 1) {
                price = price.substring(0, price.length() - 1);
            } else {
                price = price.substring(0, price.length() - 2);
            }
        }

        if (price.length() >= 1) {
            gold = price;
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

        this.price = gold + "g " + silver + "s " + copper + "c";
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
        return requiredlevel;
    }

    public void setRequiredLevel(String requiredLevel) {
        this.requiredlevel = requiredLevel;
    }


    public String getPriceNum() {
        return priceNum;
    }

    public void setPriceNum(String priceNum) {
        this.priceNum = priceNum;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(level);
        dest.writeString(name_enus);
        dest.writeString(price);
        dest.writeString(quality);
        dest.writeString(requiredlevel);
        dest.writeString(id);
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

}
