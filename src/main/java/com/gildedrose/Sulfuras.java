package com.gildedrose;

public class Sulfuras extends TypedItem {
    public Sulfuras(Item item) {
        super(item);
    }

    public void update() {
        logger.info("Le Sulfuras ne se dégrade jamais");
        qualityCheck();
    }
}
