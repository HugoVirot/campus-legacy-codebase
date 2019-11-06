package com.gildedrose;

public class AgedBrie extends TypedItem {
    public AgedBrie(Item item) {
        super(item);
    }

    public void update() {
        item.sellIn--;
        if (item.sellIn >= 0) {
            item.quality++;
        } else {
            item.quality += 2;
        }
        qualityCheck();
    }
}
