package com.gildedrose;

public class Backstage extends TypedItem {
    public Backstage(Item item) {
        super(item);
    }

    public void update() {
        item.sellIn--;
        logger.info("il s'agit d'un concert");
        increaseQuality();

        if (item.sellIn < 11) {
            increaseQuality();
        }
        if (item.sellIn < 6) {
            increaseQuality();
        }
        if (item.sellIn < 0) {
            item.quality = 0;
            logger.info("La date du concert est dépassée la qualité est remise à 0");
        }
    }
}
