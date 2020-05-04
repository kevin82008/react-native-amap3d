package cn.qiuxiang.react.amap3d.maps

import com.facebook.react.bridge.ReadableArray
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

@Suppress("unused")
internal class AMapClusterPointManager : SimpleViewManager<AMapClusterPoint>() {
    override fun getName(): String {
        return "AMapClusterPoint"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): AMapClusterPoint {
        return AMapClusterPoint(reactContext)
    }

    override fun getExportedCustomDirectEventTypeConstants(): Map<String, Any>? {
        return MapBuilder.of(
                "onItemPress", MapBuilder.of("registrationName", "onItemPress")
        )
    }

    @ReactProp(name = "points")
    fun setPoints(clusterPoint: AMapClusterPoint, points: ReadableArray) {
        clusterPoint.setPoints(points)
    }

    @ReactProp(name = "image")
    fun setImage(clusterPoint: AMapClusterPoint, image: String) {
        clusterPoint.setImage(image);
    }
}