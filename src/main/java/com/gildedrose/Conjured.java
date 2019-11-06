package com.gildedrose;

public class Conjured extends TypedItem {
    public Conjured(Item item) {
        super(item);
    }

    public void update() {
        item.sellIn--;
        if (item.sellIn >= 0) {
            item.quality -= 2;

        } else {
            item.quality -= 4;
        }
        qualityCheck();
    }
}
