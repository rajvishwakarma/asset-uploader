package com.sample.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sample.models.Asset;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AssetListResponse {
	
	private List<Asset> assets;
	private String message;
	
	public AssetListResponse(List<Asset> assets, String message) {
		this.assets = assets;
		this.message = message;
	}
	
}
