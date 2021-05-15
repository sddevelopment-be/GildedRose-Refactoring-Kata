package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class GildedRoseIntegrationTest {

  @Test
  void givenAStartingInventoryStock_whenCreatingAnInn_theInventoryIsPresent() {
    GildedRose inn = InnTestUtilities.createInnWithSingleItem(new Item("foo", 0, 0));

    Assertions.assertThat(inn.items[0])
        .isNotNull()
        .extracting("name")
        .isEqualTo("foo");
  }

}
