package com.momo.zoo.ui.pavilion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.momo.zoo.R
import com.momo.zoo.databinding.FragmentPavilionBinding

class PavilionFragment : Fragment() {

    private lateinit var mBinding: FragmentPavilionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pavilion, container, false)
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val args: PavilionFragmentArgs by navArgs()

        with(mBinding) {
            imageView3.transitionName = args.dataPosition._id.toString() + "image"
            textView5.transitionName = args.dataPosition._id.toString() + "text"
            textView6.transitionName = args.dataPosition._id.toString() + "text2"
            Glide.with(this@PavilionFragment)
                .load(args.dataPosition.E_Pic_URL)
                .centerCrop()
                .into(imageView3)
            textView5.text = args.dataPosition.E_Info
            if (args.dataPosition.E_Info == "") {
                textView6.text = "無休館資訊"
            } else {
                textView6.text = args.dataPosition.E_Memo
            }
            textView7.text = args.dataPosition.E_Category

            (activity as AppCompatActivity).supportActionBar?.setTitle(args.dataPosition.E_Name)
        }
    }
}