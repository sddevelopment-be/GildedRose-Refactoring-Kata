package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class InnDefaultBehaviourTest {

  public static final int DEFAULT_MAX_QUALITY = 50;
  public static final int DEFAULT_DEGRADATION_AMOUNT = 1;

  @Test
  void givenAStartingInventoryStock_whenCreatingAnInn_theInventoryIsPresent() {
    Item[] startingInventory = new Item[]{new Item("foo", 0, 0)};

    GildedRose inn = new GildedRose(startingInventory);

    Assertions.assertThat(inn.items[0])
        .isNotNull()
        .extracting("name")
        .isEqualTo("foo");
  }

  @Test
  void givenADefaultMaxQualityItem_whenTimeAdvances_thenQualityDecreasesByDefaultDegradationAmount() {
    Item mochacino = new Item("coffee", 10, DEFAULT_MAX_QUALITY);
    GildedRose inn = new GildedRose(new Item[]{mochacino});
    Assertions.assertThat(inn.items).hasSize(1);

    timePasses(inn);

    Assertions.assertThat(inn.items[0])
        .isNotNull()
        .extracting("quality")
        .isEqualTo(DEFAULT_MAX_QUALITY - DEFAULT_DEGRADATION_AMOUNT);

  }

  private void timePasses(final GildedRose inn) {
    inn.updateQuality();
  }

}
