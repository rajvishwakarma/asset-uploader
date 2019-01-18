package com.sample.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.enums.AssetStatus;
import com.sample.models.Asset;
import com.sample.repo.AssetRepo;
import com.sample.response.AssetResponse;
import com.sample.utils.ResponseMessages;

@RunWith(SpringRunner.class)
public class AssetServiceImplTest {
	
	@TestConfiguration
    static class AssetServiceImplTestContextConfiguration {
  
        @Bean
        public AssetServiceImpl assetService() {
            return new AssetServiceImpl();
        }
    }
	
	
	@MockBean
	AssetRepo assetRepo;
	
	@Autowired
	AssetServiceImpl assetService;
	
	@Test
	public void postAsset() throws Exception
	{
	    MockMultipartFile file = new MockMultipartFile("field1", "", "text/plain", "field1 data".getBytes());
	    
	    AssetResponse response = assetService.postAsset(file);
	    
	    Asset asset = null;
	    Optional<Asset> assetOptional = assetRepo.findById(response.getId());
		if (assetOptional.isPresent())
		    asset = assetOptional.get();
		
		assertEquals("fieild1", asset.getDownloadURL());
	    assertEquals(AssetStatus.UPLOADED, asset.getStatus());
	}
	
	@Test
	public void updateAsset() throws Exception
	{
	    Asset asset1 = assetRepo.save(new Asset("http://someurl1"));
	    
	    AssetResponse response = assetService.updateAsset(asset1.getId(), AssetStatus.ARCHIVED);
	    		
	    Asset asset2 = null;
	    Optional<Asset> assetOptional = assetRepo.findById(response.getId());
		if (assetOptional.isPresent())
		    asset2 = assetOptional.get();
		
	    assertEquals(asset1.getStatus(), asset2.getStatus());
	    
	}
	
	@Test
	public void getAssetURL() throws Exception
	{
	    Asset asset1 = assetRepo.save(new Asset("http://someurl1"));
	    asset1 = assetRepo.save(asset1);
	    
	    AssetResponse response1 = assetService.getAssetURL(asset1.getId());
	    assertEquals(response1.getMessage(), ResponseMessages.ASSET_ARCHIVED);
	    
	    Asset asset2 = assetRepo.save(new Asset("http://someurl2"));
	    asset2.setStatus(AssetStatus.ARCHIVED);
	    asset2 = assetRepo.save(asset2);
	    
	    AssetResponse response2 = assetService.getAssetURL(asset2.getId());
	    assertEquals(response2.getMessage(), ResponseMessages.SUCCESS);
	}
	
	@Test
	public void getAssetList() throws Exception
	{
		Asset asset1 = new Asset("http://someurl1", AssetStatus.UPLOADED);
    	assetRepo.save(asset1);
    	
    	Asset asset2 = new Asset("http://someurl2", AssetStatus.ARCHIVED);
    	assetRepo.save(asset2);
    	
    	Asset asset3 = new Asset("http://someurl3", AssetStatus.UPLOADED);
    	assetRepo.save(asset3);
    	
    	Asset asset4 = new Asset("http://someurl4", AssetStatus.ARCHIVED);
    	assetRepo.save(asset4);
     
    	List<Asset> assets = assetService.getAssetList();
        
    	assertEquals(2, assets.size());
	}
}
