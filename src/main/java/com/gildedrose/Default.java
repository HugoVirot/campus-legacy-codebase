package com.gildedrose;

public class Default extends TypedItem {
    public Default(Item item) {
        super(item);
    }

    public void update() {
        item.sellIn--;
        if (item.quality > 0) {
            logger.info("Normal item quality > 0 || quality - 1");
            item.quality--;
        }
        if (item.sellIn < 0) {
            if (item.quality > 0) {
                logger.info("Normal item sellIn < 0, quality > 0 || quality - 1");
                item.quality--;
            }
        }
        qualityCheck();
    }
}
