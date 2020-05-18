package com.airbag.dis.disfoot.ui.main.tutorial

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.navigation.fragment.findNavController
import com.airbag.dis.disfoot.R
import kotlinx.android.synthetic.main.fragment_tutorial.*
import java.util.*

class FragmentTutorial : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupVideo()
        fragmentTutorialBtnClose.setOnClickListener { findNavController().popBackStack() }
        fragmentTutorialvideoView.setOnCompletionListener { findNavController().popBackStack()  }
    }

    private fun setupVideo()
    {
        var videoPath = ""
        when(Locale.getDefault().language){
            "it" -> videoPath = "android.resource://${activity?.packageName}/${R.raw.video_it}"
            "en" -> videoPath = "android.resource://${activity?.packageName}/${R.raw.video_en}"
            else -> videoPath = "android.resource://${activity?.packageName}/${R.raw.video_en}"
        }
        val videoUri = Uri.parse(videoPath)
        fragmentTutorialvideoView.setVideoURI(videoUri)
        fragmentTutorialvideoView.setMediaController( MediaController(context).also { it.setAnchorView(fragmentTutorialvideoView) })
        fragmentTutorialvideoView.start()

    }
}
