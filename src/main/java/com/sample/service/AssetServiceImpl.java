package com.sample.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sample.enums.AssetStatus;
import com.sample.models.Asset;
import com.sample.repo.AssetRepo;
import com.sample.response.AssetResponse;
import com.sample.utils.AmazonClient;
import com.sample.utils.ResponseMessages;

@Service
public class AssetServiceImpl implements IAssetService {
	
	Logger LOG = LoggerFactory.getLogger(AssetServiceImpl.class);
	
	@Autowired
	private AmazonClient amazonClient;
	
	@Autowired
	private AssetRepo assetRepo;
	
	@Override
	public AssetResponse postAsset(MultipartFile file) {
		
		try {
			LOG.info("Uploading file to S3...");
			
			String assetURL = amazonClient.uploadFile(file);
			
			Asset asset = new Asset(assetURL);
			asset.setStatus(AssetStatus.UPLOADED);
			//save asset
			asset = assetRepo.save(asset);
			
			LOG.info("File Successfully Uploaded to S3");
			
			return new AssetResponse(asset.getId(), asset.getStatus().name(), ResponseMessages.SUCCESS);
		} catch (Exception e) {
			
			LOG.error("File Failed to Upload");
			return null;
		}
	}

	@Override
	public AssetResponse updateAsset(Integer assetId, AssetStatus status) {
		
		LOG.info("Updating File status...");
		Optional<Asset> assetOptional = assetRepo.findById(assetId);
		if (assetOptional.isPresent()){
		    Asset asset = assetOptional.get();
		    asset.setStatus(status);
		    //update asset
		    assetRepo.save(asset);
		    
		    LOG.info("File's status successfully updated");
		    return new AssetResponse(ResponseMessages.SUCCESS);
		}
		return new AssetResponse(ResponseMessages.RECORD_NOT_FOUND);
	}

	@Override
	public AssetResponse getAssetURL(Integer assetId) {
		
		LOG.info("Retreiving File downloadURL...");
		Optional<Asset> assetOptional = assetRepo.findById(assetId);
		if (assetOptional.isPresent()){
		    Asset asset = assetOptional.get();
		    if(asset.getStatus().equals(AssetStatus.ARCHIVED))
		    	return new AssetResponse(ResponseMessages.ASSET_ARCHIVED);
		    
		    LOG.info("File retreived successfully");
		    return new AssetResponse(asset.getDownloadURL(), ResponseMessages.SUCCESS);
		}
		return new AssetResponse(ResponseMessages.RECORD_NOT_FOUND);
	}

	@Override
	public List<Asset> getAssetList() {
		LOG.info("Retreiving all assets...");

		return (List<Asset>)assetRepo.findAllAssets();
	}

}
