package com.sample.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AssetResponse {
	
	private Integer id;
	private String status;
	private String message;
	private String downloadURL;
	
	public AssetResponse(Integer id, String status, String message) {
		
		this.id = id;
		this.status = status;
		this.message = message;
	}
	
	public AssetResponse(String downloadURL, String message) {
		
		this.downloadURL = downloadURL;
		this.message = message;
	}
	
	public AssetResponse(String message) {
		
		this.message = message;
	}
	
}
