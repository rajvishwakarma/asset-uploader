package com.sample.repo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.enums.AssetStatus;
import com.sample.models.Asset;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AssetRepoTest {
	 
	@Autowired
    private AssetRepo assetRepo;
	
	@Autowired
	private TestEntityManager entityManager;
    
    @Test
    public void whenFindAllAssets_thenReturnListOfAssets() {
        
    	Asset asset1 = new Asset("http://someurl1", AssetStatus.UPLOADED);
    	entityManager.persist(asset1);
    	
    	Asset asset2 = new Asset("http://someurl2", AssetStatus.ARCHIVED);
    	entityManager.persist(asset2);
    	
    	Asset asset3 = new Asset("http://someurl3", AssetStatus.UPLOADED);
    	entityManager.persist(asset3);
    	
    	Asset asset4 = new Asset("http://someurl4", AssetStatus.ARCHIVED);
    	entityManager.persist(asset4);
     
    	List<Asset> assets = assetRepo.findAllAssets();
        
    	assertEquals(2, assets.size());
    }
    
    @Test
	public void saveAsset() throws Exception
	{
	    Asset asset = new Asset("http://someurl/field1", AssetStatus.UPLOADED);
	    
	    Asset assetRetreived = assetRepo.save(asset);
	    Assert.assertEquals(asset.getDownloadURL(), assetRetreived.getDownloadURL());
	    Assert.assertEquals(asset.getStatus(), assetRetreived.getStatus());
	}
 
}
