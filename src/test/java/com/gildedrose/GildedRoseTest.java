package com.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

    @Test
    void testSellIn(){
        Item[] items = new Item[] { new Item("épée", 5, 5 )};
        GildedRose app = new GildedRose(items);
        int days = 5;
        for (int i =0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].sellIn).isEqualTo(0);
    }

    @Test
    void testSulfuras(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80 )};
        GildedRose app = new GildedRose(items);
        int days = 5;
        for (int i =0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].sellIn).isEqualTo(0);
        assertThat(app.items[0].quality).isEqualTo(80);
    }

    @Test
    void qualityNeverNegative(){
        Item[] items = new Item[]{new Item("TestQuality",10,-10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    void testBrieQuality(){
        Item[] items = new Item[]{new Item("Aged Brie",3,0)};
        GildedRose app = new GildedRose(items);
        int days = 6;
        for (int i =0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(9);
    }

    @Test
    void testQualityNeverOver50(){
        Item[] items = new Item[]{new Item("épée",3,70)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(50);
    }

    @Test
    void testConjured(){
        Item[] items = new Item[]{new Item("Conjured mana cake",3,40)};
        GildedRose app = new GildedRose(items);
        int days = 6;
        for (int i =0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(22);
    }

    @Test
    void testBackstage(){
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",15,0)};
        GildedRose app = new GildedRose(items);
        int days = 14;
        for (int i =0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(29);
    }

    @Test
    void testBackstageSellInNegative(){
        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert",-1,10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertThat(app.items[0].quality).isEqualTo(0);
    }

    @Test
    void testWineQuality50(){
        Item[] items = new Item[] { new Item("Red red wine", 600,50) };
        GildedRose app = new GildedRose(items);
        int days = 50;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(100);
        assertThat(app.items[0].sellIn).isEqualTo(550);
    }

    @Test
    void testWineQuality300(){
        Item[] items = new Item[] { new Item("Red red wine", 600,50) };
        GildedRose app = new GildedRose(items);
        int days = 300;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(350);
        assertThat(app.items[0].sellIn).isEqualTo(300);
    }

    @Test
    void testWineQuality600(){
        Item[] items = new Item[] { new Item("Red red wine", 600,50) };
        GildedRose app = new GildedRose(items);
        int days = 600;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(350);
        assertThat(app.items[0].sellIn).isEqualTo(0);
    }

    @Test
    void testWineQuality601(){
        Item[] items = new Item[] { new Item("Red red wine", 600,50) };
        GildedRose app = new GildedRose(items);
        int days = 601;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(349);
        assertThat(app.items[0].sellIn).isEqualTo(-1);
    }

    @Test
    void testWineQuality700(){
        Item[] items = new Item[] { new Item("Red red wine", 600,50) };
        GildedRose app = new GildedRose(items);
        int days = 700;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(250);
        assertThat(app.items[0].sellIn).isEqualTo(-100);
    }

    @Test
    void testDefault(){
        Item[] items = new Item[] { new Item("épée", 10,30) };
        GildedRose app = new GildedRose(items);
        int days = 15;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }
        assertThat(app.items[0].quality).isEqualTo(10);
    }


}
