package com.airbag.dis.disfoot.ui.main.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.airbag.dis.disfoot.R
import kotlinx.android.synthetic.main.fragment_info.*

class FragmentInfo : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBtnClose.setOnClickListener { findNavController().popBackStack() }
        fragmentInfoBtnPrivacy.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse
                        (getString(R.string.uri_privacy))
                )
            )
        }
        fragmentInfoBtnTc.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse
                        (getString(R.string.uri_terms))
                )
            )
        }
        fragmentInfoTxtVersion.setText("v " +
            context?.packageManager?.getPackageInfo(
                context?.packageName,
                0
            )?.versionName
        )
    }
}
