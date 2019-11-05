package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class GildedRose {

    Logger logger = LoggerFactory.getLogger(GildedRose.class);
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {                  // on parcourt la liste des items

            if (items[i].name.isEmpty()){
                logger.error("attention : objet sans nom !");
                items[i].name = "inconnu";
            }
            if (items[i].quality < 0){
                logger.error("attention : objet avec qualité négative!");
                items[i].quality = 20;
            }

            logger.info("item ancien statut : nom : {}, qualité : {}, jours : {} ", items[i].name, items[i].quality, items[i].sellIn);
            items[i].sellIn -= 1;                           // l'item perd 1 jour de vente

//**************************************cas du brie****************************************************

            if (items[i].name.equals("Aged Brie")) {
                logger.info("il s'agit d'un Aged Brie");
                increaseQuality(items[i]);
                if (items[i].sellIn < 0){
                    increaseQuality(items[i]);
                }

//**************************************cas du concert****************************************************

            } else if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                logger.info("il s'agit d'un concert");
                increaseQuality(items[i]);  // dans tous les cas : qualité +1

                if (items[i].sellIn < 11) {         // concert avec 10 jours ou moins restants
                    increaseQuality(items[i]);
                }
                if (items[i].sellIn < 6) {          // concert avec 5 jours ou moins (rentre dans les deux cas : deux fois +1)
                    increaseQuality(items[i]);
                }
                if (items[i].sellIn < 0) {              // si date dépassée => qualité passe à 0
                    items[i].quality = 0;
                }
            }

//**************************************cas du sulfuras****************************************************

            else if (items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                logger.info("il s'agit d'un sulfuras");
                items[i].sellIn += 1;                           // l'item gagne 1 jour de vente (annulation jour perdu)
                System.out.println("le sulfuras ne s'altère jamais");
            }

//*********************************cas des produits conjured + autres cas****************************************************

            else {
                decreaseQuality(items[i]);                                    // tous les cas : q -1 ou -2 selon jrs restants
                if (items[i].name.matches("(.*)Conjured(.*)")) {           //si produit avec conjured dans nom : idem
                    logger.info("Il s'agit d'un produit Conjured");
                    decreaseQuality(items[i]);
                } else {
                    logger.info("il s'agit d'un produit normal, hors produits spéciaux");
                }
            }
        logger.info("item nouveau statut : nom : {}, qualité : {}, jours : {} ", items[i].name, items[i].quality, items[i].sellIn);
            System.out.println();
        }
    }

    public Item[] getItems() {
        return items;
    }

    public void increaseQuality(Item item) {    // sa qualité gagne +1 si inférieure à 50
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    public void decreaseQuality(Item item) {   //qualité -1 ou -2 selon jours restants
        if (item.quality > 0) {
            if (item.sellIn >= 0) {                    // jours restants
                item.quality -= 1;
            } else {                                   // date dépassée
                if (item.quality > 2) {
                    item.quality -= 2;
                } else if (item.quality == 1){
                    item.quality -= 1;
                }
            }
        }
    }
}

