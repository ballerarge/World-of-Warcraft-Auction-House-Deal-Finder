package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import org.json.JSONObject;

/**
 * Created by decramrj on 1/19/2017.
 */

public class WowItem {

    private String id; // Done
    private String name;
    private String icon; // Done, maybe
    //private JSONObject itemSpells;
    private int itemLevel; // Done
    private String quality; // Done
    private int requiredLevel; // Done
    private boolean hasSockets; // Done
    private String subclass; // Done

    public WowItem () {
        hasSockets = false;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

//    public JSONObject getItemSpells() {
//        return itemSpells;
//    }
//
//    public void setItemSpells(JSONObject itemSpells) {
//        this.itemSpells = itemSpells;
//    }

    public int getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(int itemLevel) {
        this.itemLevel = itemLevel;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public boolean isHasSockets() {
        return hasSockets;
    }

    public void setHasSockets(boolean hasSockets) {
        this.hasSockets = hasSockets;
    }

}
