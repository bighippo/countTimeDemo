package com.example.hippo.simpledemo.ui.delivery.view

import android.os.Bundle
import com.example.hippo.simpledemo.R
import me.yokeyword.fragmentation.SupportActivity

class DeliveryActivity : SupportActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        if (findFragment(DeliveryListFragment::class.java) == null) {
            loadRootFragment(R.id.fl_container, DeliveryListFragment.newInstance())  //load root Fragment
        }
    }
}
