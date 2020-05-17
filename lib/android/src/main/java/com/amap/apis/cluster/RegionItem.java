package com.amap.apis.cluster;

import com.amap.api.maps.model.LatLng;
/**
 * Created by yiyi.qi on 16/10/10.
 */

public class RegionItem implements ClusterItem {
    private LatLng mLatLng;
    private String mTitle;
    private String mId;
    public RegionItem(LatLng latLng) {
        mLatLng=latLng;
    }

    public RegionItem(LatLng latLng,String title,String id) {
        mLatLng=latLng;
        mTitle = title;
        mId = id;
    }
    @Override
    public LatLng getPosition() {
        return mLatLng;
    }
    @Override
    public String getTitle(){
        return mTitle;
    }

    @Override
    public String getId(){
        return mId;
    }
}
