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

            TypedItem typedItem = null;

            switch (items[i].name) {
                case ("Aged Brie"):
                    typedItem = new AgedBrie(items[i]);
                    break;
                case ("Sulfuras, Hand of Ragnaros"):
                    typedItem = new Sulfuras(items[i]);
                    break;
                case ("Backstage passes to a TAFKAL80ETC concert"):
                    typedItem = new Backstage(items[i]);
                    break;
                case ("Red red wine"):
                    typedItem = new Wine(items[i]);
                    break;
                default:
                    if (items[i].name.startsWith("Conjured")){
                        typedItem = new Conjured(items[i]);
                    } else {
                        typedItem = new Default(items[i]);
                    }
            }

            typedItem.update();
            logger.info("Fin | item: " + items[i].name + ", sellIn : " + items[i].sellIn + ", quality :  " + items[i].quality);
        }

    }

    public Item[] getItems() {
        return items;
    }
}