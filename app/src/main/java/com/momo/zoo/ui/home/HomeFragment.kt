package com.momo.zoo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.transition.TransitionInflater
import com.momo.zoo.R
import com.momo.zoo.data.ZooData
import com.momo.zoo.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), EpoxyController.DetailCallbacks {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var mBinding: FragmentHomeBinding
    private val controller: EpoxyController by lazy { EpoxyController(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postponeEnterTransition()
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(mBinding) {

            lifecycleOwner = viewLifecycleOwner
            viewModel = homeViewModel
            with(epoxyHome) {

                setController(controller)
                doOnPreDraw {
                    startPostponedEnterTransition()
                }
                //fix shared element problem when back
                setRemoveAdapterWhenDetachedFromWindow(false)
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }

        homeViewModel.zooData.observe(viewLifecycleOwner, Observer {
            controller.setData(it.result.results)
        })
    }

    override fun onDetailItemClick(data: ZooData.Result.DetailResult, it: View) {
        val extras = FragmentNavigatorExtras(
            it.findViewById<ImageView>(R.id.imageView2) to (data.id).toString() + "image",
            it.findViewById<TextView>(R.id.textView3) to (data.id).toString() + "text",
            it.findViewById<TextView>(R.id.textView4) to (data.id).toString() + "text2"
        )
        findNavController().navigate(
            HomeFragmentDirections.actionNavHomeToNavPavilion(data),
            extras
        )
    }
}