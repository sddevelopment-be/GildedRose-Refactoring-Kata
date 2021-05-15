package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class DefaultItemBehaviourBusinessTest {

  public static final int DEFAULT_MAX_QUALITY = 50;
  public static final int DEFAULT_DEGRADATION_AMOUNT = 1;

  @Test
  void givenADefaultMaxQualityItem_whenTimeAdvances_thenQualityDecreasesByDefaultDegradationAmount() {
    GildedRose inn = InnTestUtilities.createInnWithSingleItem(
        new Item("+5 Dexterity Vest", 10, DEFAULT_MAX_QUALITY));

    InnTestUtilities.timeAdvances(inn);

    Assertions.assertThat(inn.items[0])
        .isNotNull()
        .extracting("quality")
        .isEqualTo(DEFAULT_MAX_QUALITY - DEFAULT_DEGRADATION_AMOUNT);

  }

  @Test
  void givenADefaultItem_whenTimeAdvances_theSellInDataDecreases() {
    GildedRose inn = InnTestUtilities.createInnWithSingleItem(
        new Item("+5 Dexterity Vest", 10, DEFAULT_MAX_QUALITY));

    InnTestUtilities.timeAdvances(inn);

    Assertions.assertThat(inn.items[0].sellIn).isEqualTo(9);
  }

  @Test
  void givenADefaultItem_whenSellByDateIsExceeded_theQualityDegradationDoubles() {
    GildedRose inn = InnTestUtilities.createInnWithSingleItem(
        new Item("+5 Dexterity Vest", 10, DEFAULT_MAX_QUALITY));

    InnTestUtilities.timeAdvances(inn, 11);

    Assertions.assertThat(inn.items[0].quality)
        .isEqualTo(DEFAULT_MAX_QUALITY - 12);
  }

  @Test
  void givenAnItem_whenSurpassingTheSellInDate_theQualityIsReducedToZero() {
    GildedRose inn = InnTestUtilities.createInnWithSingleItem(new Item("Piece of wool", 2, 2));

    InnTestUtilities.timeAdvances(inn, 250);

    Assertions.assertThat(inn.items[0].quality).isZero();
  }

  @Test
  void givenAnItem_whenDegradingTheItem_theQualityCanNeverBeNegative() {
    GildedRose inn = InnTestUtilities.createInnWithSingleItem(new Item("Piece of wool", 2, 2));

    InnTestUtilities.timeAdvances(inn, 5);

    Assertions.assertThat(inn.items[0].quality).isNotNegative();
  }

}
