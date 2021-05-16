package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscoredCamelCasing.class)
public class EventTicketsBusinessTest {

  @Test
  void givenTicketsToAnEvent_whenTheEventIsLessThanTenDaysAway_TheQualityIncreasesByTwo() {
    GildedRose theGildedRose = InnTestUtilities
        .createInnWithSingleItem(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));

    InnTestUtilities.timeAdvances(theGildedRose, 7);

    Assertions.assertThat(theGildedRose.items[0].quality).isEqualTo(29);
  }

  @Test
  void givenTicketsToAnEvent_whenTheEventIsLessThanThreeDaysAway_TheQualityIncreasesByThree() {
    GildedRose theGildedRose = InnTestUtilities
        .createInnWithSingleItem(new Item("Backstage passes to a TAFKAL80ETC concert", 3, 20));

    InnTestUtilities.timeAdvances(theGildedRose, 3);

    Assertions.assertThat(theGildedRose.items[0].quality).isEqualTo(29);
  }

  @Test
  void givenTicketsToAnEvent_whenTheEventHasHappened_TheQualityIsReducedToZero() {
    GildedRose theGildedRose = InnTestUtilities
        .createInnWithSingleItem(new Item("Backstage passes to a TAFKAL80ETC concert", 3, 20));

    InnTestUtilities.timeAdvances(theGildedRose, 5);

    Assertions.assertThat(theGildedRose.items[0].quality).isEqualTo(0);
  }

}
