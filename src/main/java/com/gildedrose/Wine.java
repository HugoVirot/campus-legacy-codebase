package com.gildedrose;

public class Wine extends TypedItem {
    public Wine(Item item) {
        super(item);
    }

    public void update() {
        item.sellIn--;
        if (item.sellIn >= 600) {
            logger.info("pas d'évolution de la qualité du vin");
        } else if (item.sellIn < 600 && item.sellIn >= 300) {
            item.quality++;
            logger.info("le vin se bonifie d'un point: sa valeur est maintenant de {} !", item.quality);
        } else if (item.sellIn < 300 && item.sellIn >= 0) {
            logger.info("pas d'évolution de la qualité du vin");
        } else {
            if (item.quality > 0) {
                item.quality--;
                logger.info("le vin se dégrade d'un point: sa valeur est maintenant de {} !", item.quality);
            } else {
                logger.info("qualité à 0 : ne peut pas descendre");
            }
        }
    }

}
