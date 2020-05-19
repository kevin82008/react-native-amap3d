package cn.qiuxiang.react.amap3d.maps

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.amap.api.maps.AMap.InfoWindowAdapter
import com.amap.api.maps.model.Marker
import android.widget.Toast
import com.amap.apis.cluster.Cluster;
class AMapInfoWindowAdapter(
        private val context: Context,
        private val markers: HashMap<String, AMapMarker>,
        private val clusters: HashMap<String, AMapClusterPoint>
) : InfoWindowAdapter {
    val paddingTop = context.resources.displayMetrics.density

    override fun getInfoWindow(marker: Marker): View? {
        Toast.makeText(context, "" + marker.getTitle(), Toast.LENGTH_SHORT).show();
        if(marker.getObject() is Cluster ){
            var cluster = marker.getObject() as Cluster
            Toast.makeText(context, "clusterKey:" + cluster.getClusterKey(), Toast.LENGTH_SHORT).show();
            if(null != clusters[cluster.getClusterKey()]?.infoWindow){
                Toast.makeText(context, "不为空:" + cluster.getClusterKey(), Toast.LENGTH_SHORT).show();
            }
            return clusters[cluster.getClusterKey()]?.infoWindow
        }
        Toast.makeText(context,"空window", Toast.LENGTH_SHORT).show();
        return markers[marker.id]?.infoWindow
    }

    override fun getInfoContents(marker: Marker): View? {
                
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        var title = marker.title;
        val titleView = TextView(context)
        titleView.text = marker.title
        titleView.setTextColor(Color.parseColor("#212121"))
        layout.addView(titleView)

        val snippet = marker.snippet
        if (!snippet.isEmpty()) {
            val snippetView = TextView(context)
            snippetView.text = snippet
            snippetView.maxEms = 12
            snippetView.setPadding(0, paddingTop.toInt(), 0, 0)
            snippetView.setTextColor(Color.parseColor("#757575"))
            layout.addView(snippetView)
        }

        return layout
    }
}

