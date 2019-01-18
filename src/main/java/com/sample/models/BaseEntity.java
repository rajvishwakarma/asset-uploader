package com.sample.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * BasicEntity SuperClass
 * @author RV
 *
 */
@MappedSuperclass
@Data
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BaseEntity {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSz")
	@Column(name = "created_at")
	public Date createdAt;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSz")
	@Column(name = "updated_at")
	public Date updatedAt;

	public BaseEntity(Date createdAt, Date updatedAt){
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
