package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

@DisplayName("Some items increase in value as time passes")
@DisplayNameGeneration(ReplaceUnderscoredCamelCasing.class)
public class AppreciatingItemsBusinessTest {

	@Test
	void givenAnItemThatIsKnownToAppreciate_whenTimeAdvances_thenTheQualityIncreases() {
		GildedRose inn = InnTestUtilities.createInnWithSingleItem(new Item("Aged Brie", 2, 0));

		InnTestUtilities.timeAdvances(inn);

		Assertions.assertThat(inn.getItems().get(0).quality).isEqualTo(1);
	}

	@Test
	void givenAnItemThatIsKnownToAppreciate_whenSellInDateIsExceeded_thenTheQualityIncreasesTwiceAsFast() {
		GildedRose inn = InnTestUtilities.createInnWithSingleItem(new Item("Aged Brie", 2, 0));

		InnTestUtilities.timeAdvances(inn, 4);

		Assertions.assertThat(inn.getItems().get(0).quality).isEqualTo(6);
	}

}
