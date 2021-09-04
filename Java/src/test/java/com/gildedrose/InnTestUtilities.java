package com.gildedrose;

import org.assertj.core.api.Assertions;

public final class InnTestUtilities {

	private InnTestUtilities() {
		throw new UnsupportedOperationException("Utility classes should not be instantiated");
	}

	public static void timeAdvances(final GildedRose inn) {
		inn.updateQuality();
	}

	public static void timeAdvances(final GildedRose inn, final int daysToAdvance) {
		for (int i = 0; i < daysToAdvance; i++) {
			timeAdvances(inn);
		}
	}

	public static GildedRose createInnWithSingleItem(final Item itemToStartWith) {
		GildedRose inn = GildedRose.builder()
				.item(itemToStartWith)
				.build();
		Assertions.assertThat(inn.getItems()).hasSize(1);
		return inn;
	}
}
