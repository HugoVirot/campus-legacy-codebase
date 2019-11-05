package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLOutput;

public class GildedRose {

    Logger logger = LoggerFactory.getLogger(GildedRose.class);
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {                  // on parcourt la liste des items

            logger.info("item : nom : {} qualité : {} jours : {} ", items[i].name, items[i].quality, items[i].sellIn);
            items[i].sellIn -= 1;                           // l'item perd 1 jour de vente

//**************************************cas du brie****************************************************

            if (items[i].name.equals("Aged Brie")) {
                if (items[i].quality < 50) {
                    items[i].quality += 1;                      // sa qualité gagne +1 si inférieure à 50
                }

//**************************************cas du concert****************************************************

            } else if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {

                if (items[i].sellIn < 0) {              // si date dépassée => qualité passe à 0
                    items[i].quality = 0;
                }
                if (items[i].quality < 50) {
                    if (items[i].sellIn < 11) {         // concert avec 10 jours ou moins restants
                        items[i].quality += 1;
                    }
                    if (items[i].quality < 50) {
                        if (items[i].sellIn < 6) {          // concert avec 5 jours ou moins (rentre dans les deux cas : deux fois +1)
                            items[i].quality += 1;
                        }
                    }
                }
            }

//**************************************cas du sulfuras****************************************************

            else if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].sellIn += 1;                           // l'item gagne 1 jour de vente (annulation jour perdu)
                System.out.println("le sulfuras ne s'altère jamais");
            }

//**************************************cas des produits conjured****************************************************

            else if (items[i].name.matches("(.*)Conjured(.*)")) {           //si produit avec conjured dans nom
                if (items[i].quality > 0) {
                    if (items[i].sellIn >= 0) {
                        items[i].quality = items[i].quality - 2;                    //sa qualité va baisser de 2
                    } else {
                        items[i].quality = items[i].quality - 4;                    //sa qualité va baisser de 2
                    }
                }
            }

//**********************************************autres cas*********************************************************

            else {
                if (items[i].quality > 0) {
                    if (items[i].sellIn >= 0) {
                        items[i].quality = items[i].quality - 1;       //autres produits : qualité -1 si positive
                    } else {
                        items[i].quality = items[i].quality - 2;       //autres produits : qualité -1 si positive
                    }
                }
            }
        }
    }

    public Item[] getItems() {
        return items;
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