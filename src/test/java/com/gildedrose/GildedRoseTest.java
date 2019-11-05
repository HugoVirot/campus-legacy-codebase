package com.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void testArticleSansNom() {
        Item[] items = new Item[] { new Item("", 20, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].name).isEqualTo("inconnu");
    }

    @Test
    void testQualiteNegative() {
        Item[] items = new Item[] { new Item("épée", 20, -20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    void testSulfuras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);
        int days = 10;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(80);
    }

    @Test
    void testBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", 3,0) };
        GildedRose app = new GildedRose(items);
        int days = 6;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(9);
    }

    @Test
    void testConcert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15,0) };
        GildedRose app = new GildedRose(items);
        int days = 14;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(29);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    void testVin() {
        Item[] items = new Item[] { new Item("Red red wine", 700,100) };
        GildedRose app = new GildedRose(items);
        int days = 1000;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(100);
    }

}
