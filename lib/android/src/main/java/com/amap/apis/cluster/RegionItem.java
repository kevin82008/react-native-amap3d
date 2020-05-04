package com.amap.apis.cluster;

import com.amap.api.maps.model.LatLng;
/**
 * Created by yiyi.qi on 16/10/10.
 */

public class RegionItem implements ClusterItem {
    private LatLng mLatLng;
    private String mTitle;
    public RegionItem(LatLng latLng) {
        mLatLng=latLng;
    }

    @Override
    public LatLng getPosition() {
        return mLatLng;
    }

    public String getTitle(){
        return mTitle;}
}
