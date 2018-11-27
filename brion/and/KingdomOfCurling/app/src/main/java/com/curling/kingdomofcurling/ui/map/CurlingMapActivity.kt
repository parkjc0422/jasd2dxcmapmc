package com.curling.kingdomofcurling.ui.map

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.curling.kingdomofcurling.R
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_map.*
import net.daum.android.map.coord.MapCoordLatLng
import net.daum.android.map.location.MapViewLocationManager
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint


class CurlingMapActivity : FragmentActivity() {
    companion object {
        val ADDRESS = "PLACE_ADDRESS"
        val LAT = "LAT"
        val LNG = "LNG"
        val NAME = "NAME"

        val DefaultLat = 37.53737528
        val DefaultLng = 127.00557633
    }

    var address:String? = null
    var lat:Double = 0.0
    var lng:Double = 0.0
    var name:String = ""
    var mapView: MapView? =  null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        mapView= MapView(this)

        val mapViewContainer = map_view as ViewGroup
        mapViewContainer.addView(mapView)

        address = intent.getStringExtra(ADDRESS)
        lat = intent.getDoubleExtra(LAT, DefaultLat)
        lng = intent.getDoubleExtra(LNG, DefaultLng)
        name = intent.getStringExtra(NAME)

        Thread(Runnable {
            Thread.sleep(1000)
            mapView?.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(lat, lng), 2, true)

            val marker = MapPOIItem()
            marker.itemName = name
            marker.tag = 0
            marker.mapPoint = MapPoint.mapPointWithGeoCoord(lat, lng)
            marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
            marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

            mapView?.addPOIItem(marker)
        }).start()


    }



}
