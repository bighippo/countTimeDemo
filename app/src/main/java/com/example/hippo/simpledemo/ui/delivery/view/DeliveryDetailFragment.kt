package com.example.hippo.simpledemo.ui.delivery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hippo.simpledemo.R
import com.example.hippo.simpledemo.ui.delivery.model.DeliveryModel
import com.example.hippo.simpledemo.util.AppConstants
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import me.yokeyword.fragmentation.SupportFragment

import kotlinx.android.synthetic.main.fragment_delivery_detail.*
import kotlinx.android.synthetic.main.top_bar.*

class DeliveryDetailFragment: SupportFragment(), OnMapReadyCallback {
    private lateinit var mDelivery : DeliveryModel

    private var mapViewBundle: Bundle ?= null

    companion object {
        const val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"

        fun newInstance(delivery : DeliveryModel): DeliveryDetailFragment{
            val args = Bundle()

            val fragment =  DeliveryDetailFragment()

            //set dilivery item to args
            args.putSerializable(AppConstants.DELIVERY_ITEM, delivery)

            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.

        if (savedInstanceState != null)
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY) as Bundle

    }


    //load layout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_delivery_detail, container,false)
    }

    //load data
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //get the delivery item
        mDelivery = arguments!!.getSerializable(AppConstants.DELIVERY_ITEM) as DeliveryModel

        initView()

        initListener()
    }

    //set delivery location when map ready
    override fun onMapReady(map: GoogleMap) {

        val appointLoc = LatLng(mDelivery.location.lat.toDouble(), mDelivery.location.lng.toDouble())

        map.addMarker(MarkerOptions().position(appointLoc).title("Marker"))

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(appointLoc, AppConstants.ZOOM_SCALE))
    }

    //set data to the view from the delivery info
    private fun initView(){
        //init the title
        initTitle()

        // set the delivery pic
        iv_delivery.setImageURI(mDelivery.imageUrl)

        //set the delivery description
        tv_delivery_description.text = mDelivery.description

        //set the delivery location
        tv_delivery_location.text = mDelivery.location.address


        //set delivery location
        mv_delivery_location.onCreate(mapViewBundle)

        mv_delivery_location.getMapAsync(this)
    }

    private fun initListener() {
        iv_back.setOnClickListener {
            pop()
        }
    }

    private fun initTitle() {
        tv_title.setText(R.string.delivery_detail_title)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle)
        }

        mv_delivery_location.onSaveInstanceState(mapViewBundle)
    }

    //mapView need these
    override fun onStart() {
        super.onStart()
        mv_delivery_location.onStart()
    }

    override fun onResume() {
        super.onResume()
        mv_delivery_location.onResume()
    }

    override fun onStop() {
        super.onStop()
        mv_delivery_location.onStop()
    }

    override fun onPause() {
        mv_delivery_location.onPause()
        super.onPause()
    }

    //override fun onDestroy() {
        //mv_delivery_location.onDestroy()
        //super.onDestroy()
    //}

    override fun onLowMemory() {
        super.onLowMemory()
        mv_delivery_location.onLowMemory()
    }
}