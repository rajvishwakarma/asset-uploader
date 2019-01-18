package com.sample.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sample.enums.AssetStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Asset Entity for assets
 * @author RV
 *
 */
@Entity
@Table(name = "asset")
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Asset extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Integer id;
	
	@Column(name = "download_url")
	private String downloadURL;
	
	private AssetStatus status;
	
	private Long timestamp;

	public Asset(String downloadURL) {
		super.createdAt = new Date();
		this.timestamp = new Date().getTime();
		this.downloadURL = downloadURL;
	}
	
	public Asset(String downloadURL, AssetStatus status) {
		super.createdAt = new Date();
		this.timestamp = new Date().getTime();
		this.downloadURL = downloadURL;
		this.status = status;
	}
	
}
