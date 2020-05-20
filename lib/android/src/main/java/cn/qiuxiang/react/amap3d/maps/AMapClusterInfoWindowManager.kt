package cn.qiuxiang.react.amap3d.maps

import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager

class AMapClusterInfoWindowManager : ViewGroupManager<AMapClusterInfoWindow>() {
    override fun getName(): String {
        return "AMapClusterInfoWindow"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): AMapClusterInfoWindow {
        return AMapClusterInfoWindow(reactContext)
    }
}