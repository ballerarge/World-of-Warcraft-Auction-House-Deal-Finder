package edu.rosehulman.fairchza.warcraft_auctionhouse_dealfinder;

/**
 * Created by decramrj on 2/14/2017.
 */

public class AucDisplayItem {

    private String level;
    private String name_enus;
    private String priceavg;
    private String quality;
    private String requiredlevel;
    private String buyout;

    public AucDisplayItem() {

    }

    public AucDisplayItem(String level, String name_enus, String priceavg, String quality, String requiredlevel, String buyout) {
        this.level = level;
        this.name_enus = name_enus;
        this.priceavg = priceavg;
        this.quality = quality;
        this.requiredlevel = requiredlevel;
        this.buyout = buyout;
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

    public void setName_enus(String name_enus) {
        this.name_enus = name_enus;
    }

    public String getPriceavg() {
        return priceavg;
    }

    public void setPriceavg(String priceavg) {
        this.priceavg = priceavg;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getRequiredlevel() {
        return requiredlevel;
    }

    public void setRequiredlevel(String requiredlevel) {
        this.requiredlevel = requiredlevel;
    }

    public String getBuyout() {
        return buyout;
    }

    public void setBuyout(String buyout) {
        this.buyout = buyout;
    }
}
