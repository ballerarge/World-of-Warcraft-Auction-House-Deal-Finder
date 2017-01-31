package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by decramrj on 1/20/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonInfo {
    private int nsockets;
    private int reqlevel;
    private AppearanceInfo appearances;

    public JsonInfo() {
    }

    public JsonInfo(AppearanceInfo appearances) {
        this.appearances = appearances;
    }

    public int getNsockets() {
        return nsockets;
    }

    public void setNsockets(int nsockets) {
        this.nsockets = nsockets;
    }

    public int getReqlevel() {
        return reqlevel;
    }

    public void setReqlevel(int reqlevel) {
        this.reqlevel = reqlevel;
    }

    public AppearanceInfo getAppearances() {
        return appearances;
    }

    public void setAppearances(AppearanceInfo info) {
        this.appearances = info;
    }

    public class AppearanceInfo {
        @JsonProperty("0")
        private Object[] items;

        public AppearanceInfo() {
        }

        public Object[] getItems() {
            return items;
        }

        public void setItems(Object[] items) {
            this.items = items;
        }
    }
}
