package cn.qiuxiang.react.amap3d.maps

import android.content.Context
import cn.qiuxiang.react.amap3d.toLatLng
import cn.qiuxiang.react.amap3d.toWritableMap
import com.amap.api.maps.AMap
import com.amap.api.maps.model.*
import com.amap.apis.cluster.*


import com.facebook.react.views.view.ReactViewGroup
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.events.RCTEventEmitter

class AMapClusterPoint(context: Context) : ReactViewGroup(context), AMapOverlay {
    private var overlay: ClusterOverlay? = null
    private var items: ArrayList<ClusterItem> = ArrayList()
    private var icon: BitmapDescriptor? = null
    private var clusterRadius: Float = 100.0f
    private val eventEmitter: RCTEventEmitter = (context as ThemedReactContext).getJSModule(RCTEventEmitter::class.java)
    
    fun setPoints(points: ReadableArray) {
        items = ArrayList((0 until points.size())
                .map {
                    val data = points.getMap(it)
                
                    val lat = Math.random() + 39.474923
                    val lon = Math.random() + 116.027116
                    val latLng = LatLng(lat, lon, false)
                    val regionItem = RegionItem(latLng)
                    items.add(regionItem)
             
                    val item = RegionItem(data!!.toLatLng())
                    // if (data!!.hasKey("title")) {
                    //     item.setTitle(data!!.getString("title"))
                    // }
                    item
                })
        overlay?.setClusterItems(items)
    }

    override fun add(map: AMap) {
        overlay = ClusterOverlay(map, items,
        dp2px(context, clusterRadius),context)
        overlay!!.setOnClusterClickListener(object : ClusterClickListener {
            override fun onClick(marker:Marker, clusterItems:List<ClusterItem>) {
                val size: Int = clusterItems.size
                val data = Arguments.createMap()
                data.putInt("nums", size)
                var latLng: LatLng = marker.getPosition();
                data.putMap("latLng", latLng.toWritableMap())
                emit(id.hashCode(), "clusterPointClick", data)
            }
        })
    }

    fun emit(id: Int, event: String, data: WritableMap) {
        eventEmitter.receiveEvent(id, event, data)
    }

    override fun remove() {
        overlay?.onDestroy()
    }

    fun setImage(image: String) {
        val drawable = context.resources.getIdentifier(image, "drawable", context.packageName)
        icon = BitmapDescriptorFactory.fromResource(drawable)
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale: Float = context.getResources().getDisplayMetrics().density
        return (dpValue * scale + 0.5f).toInt()
    }
   
}