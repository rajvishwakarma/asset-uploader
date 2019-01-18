package com.sample.enums;

/**
 * AssetStatus handles different status for Assets
 * @author RV
 *
 */
public enum AssetStatus {
	
	UPLOADED(0),
	ARCHIVED(1);

	public final int value;

	AssetStatus(final int value) {
		this.value = value;
	}

	public static AssetStatus getKeyByValue(int value) {

		for(AssetStatus e: AssetStatus.values()) {
			if(e.value == value) {
				return e;
			}
		}
		return null;
	}
}
