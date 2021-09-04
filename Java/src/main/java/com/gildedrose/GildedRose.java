package com.gildedrose;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Getter
@Builder
@AllArgsConstructor
class GildedRose {

	@Singular List<Item> items = new ArrayList<>();

	public GildedRose(Item[] items) {
		this(asList(items));
	}

	public void updateQuality() {
		for (Item item: items) {
			if (!isEventTicket(item) && !isAppreciatingWithAge(item)) {
				if (item.getQuality() > 0) {
					if (!isLegendaryItem(item)) {
						item.setQuality(devaluate(item));
					}
				}
			} else {
				if (item.getQuality() < 50) {
					item.setQuality(appreciate(item));

					if (isEventTicket(item)) {
						if (item.sellIn < 11) {
							if (item.getQuality() < 50) {
								item.setQuality(appreciate(item));
							}
						}

						if (item.sellIn < 6) {
							if (item.getQuality() < 50) {
								item.setQuality(appreciate(item));
							}
						}
					}
				}
			}

			if (!isLegendaryItem(item)) {
				item.sellIn = item.sellIn - 1;
			}

			 if (item.sellIn < 0) {
				if (!isAppreciatingWithAge(item)) {
					if (!isEventTicket(item)) {
						if (item.getQuality() > 0) {
							if (!isLegendaryItem(item)) {
								item.setQuality(devaluate(item));
							}
						}
					} else {
						item.setQuality(0);
					}
				} else {
					if (item.getQuality() < 50) {
						item.setQuality(appreciate(item));
					}
				}
			}
		}
	}

	private boolean isAppreciatingWithAge(Item item) {
		return item.getName().equals("Aged Brie");
	}

	private boolean isLegendaryItem(Item item) {
		return item.getName().equals("Sulfuras, Hand of Ragnaros");
	}

	private boolean isEventTicket(Item item) {
		return item.getName().equals("Backstage passes to a TAFKAL80ETC concert");
	}

	private int appreciate(Item item) {
		return item.getQuality() + 1;
	}

	private int devaluate(Item item) {
		return item.getQuality() - 1;
	}
}
