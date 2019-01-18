package com.sample.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sample.models.Asset;

@RepositoryRestResource(path = "assets", exported = true)
public interface AssetRepo extends CrudRepository<Asset, Integer> {
	
	@Query(nativeQuery = false, value = "Select id, timestamp from Asset where status != 1")
	List<Asset> findAllAssets();
	
}
