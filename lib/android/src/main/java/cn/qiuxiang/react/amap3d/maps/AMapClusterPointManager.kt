package cn.qiuxiang.react.amap3d.maps

import com.facebook.react.bridge.ReadableArray
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import android.view.View


@Suppress("unused")
internal class AMapClusterPointManager : ViewGroupManager<AMapClusterPoint>() {

    companion object {
        const val ShowInfoWindow = 1
    }

    override fun getCommandsMap(): Map<String, Int> {
        return mapOf(
                "showInfoWindow" to ShowInfoWindow
        )
    }

    override fun receiveCommand(marker: AMapClusterPoint, commandId: Int, args: ReadableArray?) {
        when (commandId) {
            ShowInfoWindow -> marker.showInfoWindow(args)
        }
    }


    override fun getName(): String {
        return "AMapClusterPoint"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): AMapClusterPoint {
        return AMapClusterPoint(reactContext)
    }

    override fun getExportedCustomDirectEventTypeConstants(): Map<String, Any>? {
        return MapBuilder.of(
                "clusterPointClick", MapBuilder.of("registrationName", "onClusterPointClick")
        )
    }

    override fun addView(clusterPoint: AMapClusterPoint, view: View, index: Int) {
        when (view) {
            is AMapClusterInfoWindow -> {
                clusterPoint.infoWindow = view
            }
            else -> super.addView(clusterPoint, view, index)
        }
    }
    
    @ReactProp(name = "points")
    fun setPoints(clusterPoint: AMapClusterPoint, points: ReadableArray) {
        clusterPoint.setPoints(points)
    }

    @ReactProp(name = "clusterKey")
    fun setPoints(clusterPoint: AMapClusterPoint, clusterKey: String) {
        clusterPoint.setClusterKey(clusterKey)
    }
    

    @ReactProp(name = "image")
    fun setImage(clusterPoint: AMapClusterPoint, image: String) {
        clusterPoint.setImage(image);
    }

    // @ReactProp(name = "markerId")
    // fun setMarkerId(clusterPoint: AMapClusterPoint, markerId: String) {
    //     clusterPoint.showInfoWindow(markerId);
    // }

}