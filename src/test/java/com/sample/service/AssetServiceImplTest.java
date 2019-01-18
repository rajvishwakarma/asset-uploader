package com.sample.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import com.sample.enums.AssetStatus;
import com.sample.models.Asset;
import com.sample.repo.AssetRepo;
import com.sample.response.AssetResponse;
import com.sample.utils.ResponseMessages;

@RunWith(MockitoJUnitRunner.class)
public class AssetServiceImplTest {
	
	@Mock
    private static AssetRepo assetRepo;

    @InjectMocks
    private static IAssetService assetService = new AssetServiceImpl();
	
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
	    Asset asset = new Asset(1, "http://someurl", AssetStatus.UPLOADED);
	  	
	    Optional<Asset> assetOptional = Optional.of(asset);
	    Mockito.when(assetRepo.findById(asset.getId())).thenReturn(assetOptional);

        AssetResponse response = assetService.updateAsset(asset.getId(), AssetStatus.ARCHIVED);

        Assert.assertEquals(ResponseMessages.SUCCESS, response.getMessage());
	}
	
	@Test
	public void getAssetURL() throws Exception
	{
		Asset asset1 = new Asset(1, "http://someurl1", AssetStatus.UPLOADED);
	    
		Optional<Asset> assetOptional1 = Optional.of(asset1);
	    Mockito.when(assetRepo.findById(asset1.getId())).thenReturn(assetOptional1);
		
	    AssetResponse response1 = assetService.getAssetURL(asset1.getId());
	    Assert.assertEquals(ResponseMessages.SUCCESS, response1.getMessage());
	    Assert.assertEquals(asset1.getDownloadURL(), response1.getDownloadURL());
	    
	    Asset asset2 = new Asset(2, "http://someurl2", AssetStatus.UPLOADED);
	    asset2.setStatus(AssetStatus.ARCHIVED);
	    
	    Optional<Asset> assetOptional2 = Optional.of(asset2);
	    Mockito.when(assetRepo.findById(asset2.getId())).thenReturn(assetOptional2);
	    
	    AssetResponse response2 = assetService.getAssetURL(asset2.getId());
	    Assert.assertEquals(ResponseMessages.ASSET_ARCHIVED, response2.getMessage());
	    Assert.assertNull(response2.getDownloadURL());
	}
	
	@Test
	public void getAssetList() throws Exception
	{
		List<Asset> assets = new ArrayList<>();
		
		assets.add(new Asset(1, "http://someurl1", AssetStatus.UPLOADED));
		assets.add(new Asset(2, "http://someurl1", AssetStatus.UPLOADED));
     
	    Mockito.when(assetRepo.findAllAssets()).thenReturn(assets);
		
    	List<Asset> retreivedAssets = assetService.getAssetList();
        
    	assertEquals(2, retreivedAssets.size());
	}
}
