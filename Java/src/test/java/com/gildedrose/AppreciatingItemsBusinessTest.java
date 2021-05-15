package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppreciatingItemsBusinessTest {

  @Test
  void givenAnItemThatIsKnownToAppreciate_whenTimeAdvances_TheQualityIncreases() {
    GildedRose inn = InnTestUtilities.createInnWithSingleItem(new Item("Aged Brie", 2, 0));

    InnTestUtilities.timeAdvances(inn);

    Assertions.assertThat(inn.items[0].quality).isEqualTo(1);
  }

  @Test
  void givenAnItemThatIsKnownToAppreciate_whenSellInDateIsExceeded_TheQualityIncreasesTwiceAsFast() {
    GildedRose inn = InnTestUtilities.createInnWithSingleItem(new Item("Aged Brie", 2, 0));

    InnTestUtilities.timeAdvances(inn, 4);

    Assertions.assertThat(inn.items[0].quality).isEqualTo(6);
  }

}
