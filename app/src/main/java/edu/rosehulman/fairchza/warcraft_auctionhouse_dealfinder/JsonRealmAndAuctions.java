package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import java.util.List;

/**
 * Created by fairchza on 2/12/2017.
 */

public class JsonRealmAndAuctions {
    private List<String> realms;
    private List<String> auctions;

    public JsonRealmAndAuctions(){

    }

    public JsonRealmAndAuctions(List realms, List auctions){
        this.realms = realms;
        this.auctions = auctions;
    }

    public List getRealms(){
        return this.realms;
    }

    public void setRealms(List realms){
        this.realms = realms;
    }

    public List getAuctions(){
        return this.auctions;
    }

    public void setAuctions(List auctions){
        this.auctions = auctions;
    }
}
