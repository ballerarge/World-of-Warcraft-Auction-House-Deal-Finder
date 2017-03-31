package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by decramrj on 1/19/2017.
 */

public class WowItem implements Parcelable {
    public int level;
    public String name_enus;
    public int price;
    public int quality;
    public int requiredLevel;
    public String id;

    public int getLevel() {
        return this.level;
    }

    public String getName_enus() {
        return this.name_enus;
    }

    public int getPrice() {
        return this.price;
    }

    public int getQuality() {
        return this.quality;
    }

    public int getRequiredLevel() {
        return this.requiredLevel;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQualityName() {
        switch (this.quality) {
            case 0: return "Poor";
            case 1: return "Common";
            case 2: return "Uncommon";
            case 3: return "Rare";
            case 4: return "Epic";
            case 5: return "Legendary";
            default: return "Unknown Quality";
        }
    }


    public WowItem() {}


    protected WowItem(Parcel in) {
        this.level = in.readInt();
        this.name_enus = in.readString();
        this.price = in.readInt();
        this.quality = in.readInt();
        this.requiredLevel = in.readInt();
        this.id = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.level);
        dest.writeString(this.name_enus);
        dest.writeInt(this.price);
        dest.writeInt(this.quality);
        dest.writeInt(this.requiredLevel);
        dest.writeString(this.id);
    }
}
