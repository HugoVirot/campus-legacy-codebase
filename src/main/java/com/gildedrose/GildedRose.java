package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GildedRose {

    Logger logger = LoggerFactory.getLogger(GildedRose.class);

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {                  // on parcourt la liste des items

            logger.info("item : nom : {} qualité : {} jours : {} ", items[i].name, items[i].quality, items[i].sellIn);
            if (!items[i].name.equals("Aged Brie") && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
//**********************cas général : si l'item n'est pas Aged Brie NI place concert*******************************
                if (items[i].quality > 0) {                             //si sa qualité est positive
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {  //si ce n'est pas sulfuras
                        if (items[i].name.matches("(.*)Conjured(.*)")) {           //si produit avec conjured dans nom
                            items[i].quality = items[i].quality - 2;                    //sa qualité va baisser de 2
                        } else {
                            items[i].quality = items[i].quality - 1;                    //sinon, sa qualité va baisser de 1
                        }
                    }
                } else {
//*************************cas particuliers : place de concert, brie, sulfuras*************************************
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1;

                        if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                            if (items[i].sellIn < 11) {         // concert avec 10 jours ou moins restants
                                if (items[i].quality < 50) {
                                    items[i].quality = items[i].quality + 1;
                                }
                            }

                            if (items[i].sellIn < 6) {          // concert avec 5 jours ou moins (rentre dans les deux cas : deux fois +1)
                                if (items[i].quality < 50) {
                                    items[i].quality = items[i].quality + 1;
                                }
                            }
                        }
                    }
                }

                if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {                         // si l'item n'est pas sulfuras
                    items[i].sellIn = items[i].sellIn - 1;                                           // jours -1
                }

                if (items[i].sellIn < 0) {                                      // si il n'y a plus de jours restants pour vendre le produit
                    if (!items[i].name.equals("Aged Brie")) {                                  // si pas brie
                        if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) { // si pas concert
                            if (items[i].quality > 0) {                                            // qualité > 0
                                if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {          // si pas sulfuras
                                    items[i].quality = items[i].quality - 1;                         // qualité - 1
                                }
                            }
                        } else {                                                       // cas du concert
                            items[i].quality = items[i].quality - items[i].quality;     // sa qualité passe à 0 car la date est passée
                        }
                    } else {                                                              // cas du brie
                        if (items[i].quality < 50) {
                            items[i].quality = items[i].quality + 1;                      // sa qualité gagne +1 si inférieure à 50
                        }
                    }
                }
//            if (items[i].sellIn < 0) {
//                logger.error("attention : produit {} périmé (jours de vente restants = 0) !", items[i].name);
//            }
//            if (items[i].quality < 0) {
//                logger.error("attention : produit {} créé avec qualité négative", items[i].name);
//            }
//            if (items[i].quality == 0) {
//                logger.error("attention : produit {} sans aucune valeur (0) !", items[i].name);
//            } else if (items[i].quality == 50) {
//                logger.info("qualité maximale atteinte pour le produit {} (50)", items[i].name);
//            }
            }
        }

        public Item[] getItems () {
            return items;
        }
    }