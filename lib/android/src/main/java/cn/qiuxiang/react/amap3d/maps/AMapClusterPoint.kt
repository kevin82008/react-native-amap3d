package cn.qiuxiang.react.amap3d.maps

import android.content.Context
import cn.qiuxiang.react.amap3d.toLatLng
import cn.qiuxiang.react.amap3d.toWritableMap
import com.amap.api.maps.AMap
import com.amap.api.maps.model.*
import com.amap.apis.cluster.*
import android.view.View
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
    var infoWindow: AMapClusterInfoWindow? = null
    var mClusterKey: String = "";
    fun setPoints(points: ReadableArray) {
        items = ArrayList((0 until points.size())
                .map {
                    val data = points.getMap(it)
                    val item = RegionItem(data!!.toLatLng(),data!!.getString("title"),"")
                    item.setId(data!!.getString("id")+"_" + it);
                    items.add(item)
                    item
                })
        overlay?.setClusterItems(items)
    }

    override fun addView(child: View, index: Int) {
        super.addView(child, index)
    }

    override fun add(map: AMap) {
        overlay = ClusterOverlay(mClusterKey,map, items,
        dp2px(context, clusterRadius),context)
        overlay!!.setOnClusterClickListener(object : ClusterClickListener {
            override fun onClick(marker:Marker, clusterItems:List<ClusterItem>) {
                val size: Int = clusterItems.size
                val data = Arguments.createMap()
                data.putInt("nums", size)
                var latLng: LatLng = marker.getPosition();
                data.putMap("latLng", latLng.toWritableMap())
                var zoom = map.getCameraPosition().zoom;
                data.putInt("zoom", zoom.toInt())
                data.putString("markerId", marker.getId())
                if(size == 1){
                    data.putString("id", clusterItems.get(0).id)
                }
                emit(id, "clusterPointClick", data)
            }
        })
    }

    fun emit(id: Int, event: String, data: WritableMap) {
        eventEmitter.receiveEvent(id, event, data)
    }

    override fun remove() {
        overlay?.onDestroy()
    }

    fun showInfoWindow(markerId: String){
        if(!markerId.isEmpty() && null != overlay){
            var clusterOverlay = overlay as ClusterOverlay
            var marker : Marker = clusterOverlay.getMarker(markerId);
            if(null != marker){
                marker.showInfoWindow()
            }
        }
        
    }

    fun setClusterKey(clusterKey: String) {
        mClusterKey = clusterKey;
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