package com.updown.superdo.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.updown.superdo.R
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
         inflater.inflate(R.layout.main_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.collectedDataLiveData.observe(viewLifecycleOwner, Observer {list->
            if(recyclerview_products_feed.adapter == null)
                recyclerview_products_feed.adapter =
                    ProductsFeedAdapter(list)
            else
                (recyclerview_products_feed.adapter as ProductsFeedAdapter).updateList(list)
        })
        viewModel.subscribeToSocketEvents()
    }

}
