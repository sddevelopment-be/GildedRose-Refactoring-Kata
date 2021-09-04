package com.gildedrose;

import static com.gildedrose.InnTestUtilities.createInnWithSingleItem;
import static com.gildedrose.InnTestUtilities.timeAdvances;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscoredCamelCasing.class)
class DefaultItemBehaviourBusinessTest {

	public static final int DEFAULT_MAX_QUALITY = 50;
	public static final int DEFAULT_DEGRADATION_AMOUNT = 1;

	@Test
	void givenADefaultMaxQualityItem_whenTimeAdvances_thenQualityDecreasesByDefaultDegradationAmount() {
		GildedRose inn = createInnWithSingleItem(
				new Item("+5 Dexterity Vest", 10, DEFAULT_MAX_QUALITY));

		timeAdvances(inn);

		assertThat(inn.getItems().get(0))
				.isNotNull()
				.extracting("quality")
				.isEqualTo(DEFAULT_MAX_QUALITY - DEFAULT_DEGRADATION_AMOUNT);
	}

	@Test
	void givenADefaultItem_whenTimeAdvances_theSellInDataDecreases() {
		GildedRose inn = createInnWithSingleItem(
				new Item("+5 Dexterity Vest", 10, DEFAULT_MAX_QUALITY));

		timeAdvances(inn);

		assertThat(inn.getItems().get(0).sellIn).isEqualTo(9);
	}

	@Test
	void givenADefaultItem_whenSellByDateIsExceeded_theQualityDegradationDoubles() {
		GildedRose inn = createInnWithSingleItem(
				new Item("+5 Dexterity Vest", 10, DEFAULT_MAX_QUALITY));

		timeAdvances(inn, 11);

		assertThat(inn.getItems().get(0).quality)
				.isEqualTo(DEFAULT_MAX_QUALITY - 12);
	}

	@Test
	void givenAnItem_whenSurpassingTheSellInDate_theQualityIsReducedToZero() {
		GildedRose inn = createInnWithSingleItem(new Item("Piece of wool", 2, 2));

		timeAdvances(inn, 250);

		assertThat(inn.getItems().get(0).quality).isZero();
	}

	@Test
	void givenAnItem_whenDegradingTheItem_theQualityCanNeverBeNegative() {
		GildedRose inn = createInnWithSingleItem(new Item("Piece of wool", 2, 2));

		timeAdvances(inn, 5);

		assertThat(inn.getItems().get(0).quality).isNotNegative();
	}

	@Test
	void givenAnItem_whenPrintingItsContents_theOutputContainsAllFields() {
		Item armor = new Item("Mandalorean armor", -1, 95);

		assertThat(armor.toString())
				.contains("Mandalorean armor")
				.contains("-1")
				.contains("95");
	}

}
