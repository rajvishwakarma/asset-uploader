package com.sample.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sample.enums.AssetStatus;
import com.sample.models.Asset;
import com.sample.response.AssetResponse;

public interface IAssetService {

	AssetResponse postAsset(MultipartFile file);

	AssetResponse updateAsset(Integer assetId, AssetStatus assetStatus);

	AssetResponse getAssetURL(Integer assetId);

	List<Asset> getAssetList();

}
