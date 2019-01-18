package com.sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sample.enums.AssetStatus;
import com.sample.models.Asset;
import com.sample.request.AssetRequest;
import com.sample.response.AssetListResponse;
import com.sample.response.AssetResponse;
import com.sample.service.IAssetService;
import com.sample.utils.RESTEndpointMapper;
import com.sample.utils.ResponseMessages;

/**
 * AssetController serves the REST API for assets
 * @author RV
 *
 */
@RestController
public class AssetController {
	
	Logger LOG = LoggerFactory.getLogger(AssetController.class);
	
	@Autowired
	IAssetService assetService;
	
	@PostMapping(value = RESTEndpointMapper.ASSET)
	public ResponseEntity<AssetResponse> postAsset(@RequestPart(value = "file") MultipartFile file){
		
		return new ResponseEntity<AssetResponse>(assetService.postAsset(file), HttpStatus.OK);
	}
	
	@GetMapping(value = RESTEndpointMapper.ASSET_ID)
	public ResponseEntity<AssetResponse> getAsset(@PathVariable("asset-id") Integer assetId,
			@RequestParam(name = "timeout", required = false, defaultValue = "60") Integer timeout){
		
		return new ResponseEntity<AssetResponse>(assetService.getAssetURL(assetId), HttpStatus.OK);
	}
	
	@PatchMapping(value = RESTEndpointMapper.ASSET_ID)
	public ResponseEntity<AssetResponse> updateAsset(@PathVariable("asset-id") Integer assetId, 
			@RequestBody AssetRequest assetRequest){
		
		AssetStatus assetStatus = AssetStatus.valueOf(assetRequest.getStatus());
		if(null == assetStatus)
			return new ResponseEntity<AssetResponse>(new AssetResponse(ResponseMessages.INVALID_STATUS), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<AssetResponse>(assetService.updateAsset(assetId, assetStatus), HttpStatus.OK);
	}
	
	@GetMapping(value = RESTEndpointMapper.ASSET_LIST)
	public ResponseEntity<AssetListResponse> getAssetList(){
		
		List<Asset> assets = assetService.getAssetList();
		
		return new ResponseEntity<AssetListResponse>( new AssetListResponse(assets, ResponseMessages.SUCCESS), HttpStatus.OK);
	}
}
