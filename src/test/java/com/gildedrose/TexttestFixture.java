package com.gildedrose;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TexttestFixture {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(GildedRose.class);                 // init logger
        logger.info("Début du test");

        Item[] items = new Item[]{                                                //init items
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 40)};

        GildedRose app = new GildedRose(items);                                   // instanciation nouvel objet gildedrose

        int itemsNumber = items.length;
        logger.info("Nombre d'articles en vente : {}", itemsNumber);            // affichage nombre items

        int days = 12;
        if (args.length > 0) {
            days = Integer.parseInt(args[0]) + 1;
        }

        logger.info("Nombre de jours simulés : {}", days);                     // affichage nombre jours
        System.out.println();

        for (int i = 0; i < days; i++) {                                       //boucle simulation
            System.out.println("-------- day " + i + " --------");
            System.out.println("name, sellIn, quality");
            for (Item item : items) {
                System.out.println(item);
            }
            System.out.println();
            app.updateQuality();
            System.out.println();
        }
    }
}
