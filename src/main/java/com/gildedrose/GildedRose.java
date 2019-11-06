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

            String name = items[i].name;
            int sellIn = items[i].sellIn;
            int quality = qualityCheck(items[i].quality, name);

            if (!name.equals("Sulfuras, Hand of Ragnaros")) {
                sellIn--;
            }

            if (name.startsWith("Conjured")) {
                logger.info("Il s'agit d'un produit Conjured");
                if (sellIn >= 0) {
                    quality -= 2;
                    quality = qualityCheck(quality, name);

                } else {
                    quality -= 4;
                    quality = qualityCheck(quality, name);
                }
            }

            switch (name) {
                case "Aged Brie":
                    if (sellIn >= 0) {
                        quality++;
                        quality = qualityCheck(quality, name);
                    } else {
                        quality += 2;
                        quality = qualityCheck(quality, name);
                    }
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    logger.info("Le Sulfuras ne se dégrade jamais");
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    logger.info("il s'agit d'un concert");
                    quality = increaseQuality(quality);

                    if (sellIn < 11) {
                        quality = increaseQuality(quality);
                    }
                    if (sellIn < 6) {
                        quality = increaseQuality(quality);
                    }
                    if (sellIn < 0) {
                        quality = 0;
                        logger.info("La date du concert est dépassée la qualité est remise à 0");
                    }
                    break;
                case "Red red wine":
                    if (sellIn >= 600) {
                        logger.info("pas d'évolution de la qualité du vin");
                    } else if (sellIn < 600 && sellIn >= 300) {
                        quality++;
                        logger.info("le vin se bonifie d'un point: sa valeur est maintenant de {} !", quality);
                    } else if (sellIn < 300 && sellIn >= 0) {
                        logger.info("pas d'évolution de la qualité du vin");
                    } else {
                        quality--;
                        logger.info("le vin se dégrade d'un point: sa valeur est maintenant de {} !", quality);
                    }
                    break;
                default:
                    if (!name.startsWith("Conjured")) {
                        if (quality > 0) {
                            logger.info("Normal item quality > 0 || quality - 1");
                            quality--;
                            quality = qualityCheck(quality, name);
                        }
                        if (sellIn < 0) {
                            if (quality > 0) {
                                logger.info("Normal item sellIn < 0, quality > 0 || quality - 1");
                                quality--;
                                quality = qualityCheck(quality, name);
                            }
                        }
                    }
            }
            items[i].sellIn = sellIn;
            items[i].quality = quality;

            logger.info("Fin | item: " + items[i].name + ", sellIn : " + items[i].sellIn + ", quality :  " + items[i].quality);
        }

    }

    private int qualityCheck(int quality, String name) {
        if (quality < 0) {
            logger.info("attention : objet avec qualité négative! Remise à 0 effectuée.");
            quality = 0;
        }
        if (quality > 50 && !name.equals("Sulfuras, Hand of Ragnaros") && !name.equals("Red red wine")) {
            logger.info("attention : objet avec qualité supérieure à 50 ! Remise à 50 effectuée.");
            quality = 50;
        }
        return quality;
    }

    private int increaseQuality(int quality) {
        if (quality < 50) {
            quality += 1;
        }
        return quality;
    }

    public Item[] getItems() {
        return items;
    }
}