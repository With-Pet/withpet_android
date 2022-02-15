package com.withpet.withpet_android.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.withpet.withpet_android.R
import com.withpet.withpet_android.databinding.BottomSheetCertificationBinding

class CertificationBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding : BottomSheetCertificationBinding

    companion object {
        const val TAG = "CERTIFICATION BOTTOM SHEET"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_certification, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBtnClickListener()
    }

    private fun setBtnClickListener() {
        binding.certificationImageView.setOnClickListener {

        }
        binding.certificationDateButton.setOnClickListener {

        }
        binding.certificationRegisterButton.setOnClickListener {

        }
    }
}