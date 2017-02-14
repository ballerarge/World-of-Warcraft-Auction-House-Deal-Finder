package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by fairchza on 2/12/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuctionItem {
    private Integer item;
    private Integer buyout;
    private Integer quantity;

    public AuctionItem() {

    }

    public AuctionItem(Integer itemNum, Integer buyout, Integer quantity) {
        this.item = itemNum;
        this.buyout = buyout;
        this.quantity = quantity;
    }

    public Integer getItem() {
        return this.item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getBuyout() {
        return this.buyout;
    }

    public void setBuyout(Integer buyout) {
        this.buyout = buyout;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAuc() {
        return null;
    }

    public void setAuc(Integer auc) {

    }

    public String getOwner() {
        return null;
    }

    public void setOwner(String owner) {

    }

    public String getOwnerRealm() {
        return null;
    }

    public void setOwnerRealm(String ownerRealm) {

    }

    public Integer getBid() {
        return 0;
    }

    public void setBid(Integer bid) {

    }

    public String gettimeLeft() {
        return null;
    }

    public void settimeLeft(String timeleft) {

    }

    public Integer getrand() {
        return 0;
    }

    public void setrand(Integer rand) {
    }

    public Integer getseed() {
        return 0;
    }

    public void setseed(Integer seed) {
    }

    public Integer getcontext() {
        return 0;
    }

    public void setcontext(Integer context) {
    }

    public List getbonusLists() {
        return null;
    }

    public void setbonusLists(List bonus){

    }

    public List getmodifiers(){
        return null;
    }
    public void setmodifiers(List mods){

    }
}

