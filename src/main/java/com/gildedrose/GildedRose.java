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

            String name = items[i].name;              // on stocke les attributs du produit dans des variables au nom plus court
            int sellIn = items[i].sellIn;
            int quality = negativeQualityCheck(items[i].quality);

//***********************************************cas hors sulfuras************************************
            if (!name.equals("Sulfuras, Hand of Ragnaros")) {
                logger.info("Not sulfuras || sellIn - 1");
                sellIn--;
            }

//***********************************************cas sulfuras************************************
            if (name.equals("Sulfuras, Hand of Ragnaros")) {
                logger.info("Sulfuras");

//***********************************************cas brie************************************
            } else if (name.equals("Aged Brie")) {
                if (quality < 50) {
                    if (sellIn < 0) {
                        logger.info("Aged brie, quality < 50, sellIn < 0 || quality + 2");
                        quality = quality + 2;
                    } else {
                        logger.info("Aged brie, quality < 50, sellIn > 0 || quality + 1");
                        quality++;
                    }
                }

//***********************************************cas concert************************************
            } else if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {

                if (sellIn <= 0) {                             // date passée => qualité passe à 0
                    logger.info("Backstage sellIn <= 0 || quality = 0");
                    quality = 0;
                } else {
                    if (sellIn < 11) {                        // moins de 11j restants => qualité +2
                        if (quality < 50) {
                            logger.info("Backstage sellIn < 11, quality < 50 || quality + 2");
                            quality = quality + 2;
                        }
                    }
                    if (sellIn < 6) {                        // moins de 6j restants => qualité +3 (+1 supp)
                        if (quality < 50) {
                            logger.info("Backstage sellIn < 6, quality < 50 || quality + 3");
                            quality++;
                        }
                    }
                    if (sellIn > 10) {
                        if (quality < 50) {
                            logger.info("Backstage sellIn < 6, quality < 50 || quality + 1");
                            quality++;
                        }
                    }
                }

//*******************************************cas Conjured******************************************
            } else if (name.startsWith("Conjured")) {
                if (quality > 0) {                                // qualité positive => -2
                    logger.info("Conjured item quality > 0 || quality - 2");
                    quality = quality - 2;
                    quality = negativeQualityCheck(quality);
                }
                if (sellIn < 0) {                                 // date dépassée => -2 supplémentaires
                    if (quality > 0) {
                        logger.info("Conjured item sellIn < 0, quality > 0 || quality - 2");
                        quality = quality - 2;
                        quality = negativeQualityCheck(quality);
                    }
                }

//*******************************************cas du vin rouge******************************************
            } else if (name.equals("Red red wine")) {
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

//*******************************************tous les autres cas******************************************
            } else {
                if (quality > 0) {                // qualité positive => -1
                    logger.info("Normal item quality > 0 || quality - 1");
                    quality--;
                    quality = negativeQualityCheck(quality);
                }
                if (sellIn < 0) {                 //date dépassée => -1 supplémentaires
                    if (quality > 0) {
                        logger.info("Normal item sellIn < 0, quality > 0 || quality - 1");
                        quality--;
                        quality = negativeQualityCheck(quality);
                    }
                }
            }

            items[i].sellIn = sellIn;
            items[i].quality = quality;

            logger.info("Fin | item: " + items[i].name + ", sellIn : " + items[i].sellIn + ", quality :  " + items[i].quality);
            System.out.println();
        }
    }

    private String emptyNameCheck(String name) {     // ne marche pas
//        if (name.isEmpty()) {
//            logger.error("attention : objet sans nom ! Nom \"inconnu\" attribué.");
//            name = "inconnu";
//        }
        name = "inconnu";
        return name;
    }

    private int negativeQualityCheck(int quality) {
        if (quality < 0) {
            logger.info("attention : objet avec qualité négative! Remise à 0 effectuée.");
            quality = 0;
        }
        return quality;
    }

    public Item[] getItems() {
        return items;
    }
}