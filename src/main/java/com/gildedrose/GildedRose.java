package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GildedRose {
    Item[] items;

    public static final Logger logger = LoggerFactory.getLogger(GildedRose.class);

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            logger.info("Début | item: " + items[i].name + ", sellIn : " + items[i].sellIn + ", quality :  " + items[i].quality);

            int sellIn = items[i].sellIn;
            int quality = items[i].quality;

            if(!items[i].name.equals("Sulfuras, Hand of Ragnaros")){
                logger.info("Not sulfuras || sellIn - 1");
                sellIn--;
            }

            if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                logger.info("Sulfuras");
            } else if (items[i].name.equals("Aged Brie")) {
                if (quality < 50) {
                    if (sellIn < 0) {
                        logger.info("Aged brie, quality < 50, sellIn < 0 || quality + 2");
                        quality = quality + 2;
                    } else {
                        logger.info("Aged brie, quality < 50, sellIn > 0 || quality + 1");
                        quality++;
                    }
                }
            } else if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {

                if (sellIn <= 0) {
                    logger.info("Backstage sellIn <= 0 || quality = 1");
                    quality = 0;
                } else {
                    if (items[i].sellIn < 11) {
                        if (quality < 50) {
                            logger.info("Backstage sellIn < 11, quality < 50 || quality + 2");
                            quality = quality + 2;
                        }
                    }
                    if (items[i].sellIn < 6) {
                        if (quality < 50) {
                            logger.info("Backstage sellIn < 6, quality < 50 || quality + 3");
                            quality ++;
                        }
                    }
                    if (items[i].sellIn > 10) {
                        if (quality < 50) {
                            logger.info("Backstage sellIn < 6, quality < 50 || quality + 3");
                            quality ++;
                        }
                    }
                }
            } else if (items[i].name.startsWith("Conjured")) {
                if (quality > 0) {
                    logger.info("Conjured item quality > 0 || quality - 2");
                    quality = quality - 2;
                    quality = qualityNegativeCheck(quality);
                }
                if (sellIn < 0) {
                    if (quality > 0) {
                        logger.info("Conjured item sellIn < 0, quality > 0 || quality - 2");
                        quality = quality - 2;
                        quality = qualityNegativeCheck(quality);
                    }
                }
            } else {
                if (quality > 0) {
                    logger.info("Normal item quality > 0 || quality - 1");
                    quality--;
                    quality = qualityNegativeCheck(quality);
                }
                if (sellIn < 0) {
                    if (quality > 0) {
                        logger.info("Normal item sellIn < 0, quality > 0 || quality - 1");
                        quality--;
                        quality = qualityNegativeCheck(quality);
                    }
                }
            }
            items[i].sellIn = sellIn;
            items[i].quality = quality;

            logger.info("Fin | item: " + items[i].name + ", sellIn : " + items[i].sellIn + ", quality :  " + items[i].quality);
        }
    }

    private int qualityNegativeCheck(int quality) {
        if(quality<0){
            quality = 0;
        }
        return quality;
    }

    public Item[] getItems() {
        return items;
    }
}