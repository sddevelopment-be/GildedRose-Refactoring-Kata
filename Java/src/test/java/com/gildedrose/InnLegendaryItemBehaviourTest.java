package com.gildedrose;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscoredCamelCasing.class)
public class InnLegendaryItemBehaviourTest {

	@Test
	void givenALegendaryItemInStock_whenTimePasses_TheQualityDoesNotDecrease() {
		GildedRose inn = InnTestUtilities
				.createInnWithSingleItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));

		InnTestUtilities.timeAdvances(inn);

		Assertions.assertThat(inn.items[0].quality).isEqualTo(80);
	}

	@Test
	void givenALegendaryItemInStock_whenTimePasses_TheSellInPeriodDoesNotDecrease() {
		GildedRose inn = InnTestUtilities
				.createInnWithSingleItem(new Item("Sulfuras, Hand of Ragnaros", -1, 80));

		InnTestUtilities.timeAdvances(inn);

		Assertions.assertThat(inn.items[0].sellIn).isEqualTo(-1);
	}

}
