package com.gildedrose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TypedItem {

    protected Item item;
    public static final Logger logger = LoggerFactory.getLogger(GildedRose.class);


    public TypedItem(Item item) {
        this.item = item;
    }

    public void update() {
    }

    /**
     * Update quality with min or max value.
     *
     */
    protected void qualityCheck() {
        if (item.quality < 0) {
            logger.info("attention : objet avec qualité négative! Remise à 0 effectuée.");
            item.quality = 0;
        }
        if (item.quality > 50 && !item.name.equals("Sulfuras, Hand of Ragnaros") && !item.name.equals("Red red wine")) {
            logger.info("attention : objet avec qualité supérieure à 50 ! Remise à 50 effectuée.");
            item.quality = 50;
        }
    }

    protected void increaseQuality() {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }
}
