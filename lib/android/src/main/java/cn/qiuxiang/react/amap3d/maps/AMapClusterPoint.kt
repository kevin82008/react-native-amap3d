package cn.qiuxiang.react.amap3d.maps

import android.content.Context
import cn.qiuxiang.react.amap3d.toLatLng
import com.amap.api.maps.AMap
import com.amap.api.maps.model.*
import com.amap.apis.cluster.*
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.views.view.ReactViewGroup

class AMapClusterPoint(context: Context) : ReactViewGroup(context), AMapOverlay {
    private var overlay: ClusterOverlay? = null
    private var items: ArrayList<ClusterItem> = ArrayList()
    private var icon: BitmapDescriptor? = null
    private var clusterRadius: Float = 100.0f
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
        dp2px(context, clusterRadius),
                        context)
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