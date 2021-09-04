package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
@AllArgsConstructor
public class Item {
	String name;
	int sellIn;
	int quality;

	@Override
	public String toString() {
		return this.name + ", " + this.sellIn + ", " + this.quality;
	}
}
