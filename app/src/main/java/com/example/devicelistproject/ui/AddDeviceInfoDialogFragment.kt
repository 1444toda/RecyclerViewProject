package com.example.devicelistproject.ui

import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.devicelistproject.databinding.AddDeviceInfoFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class AddDeviceInfoDialogFragment :DialogFragment() {

    companion object {
        const val REQUEST_KEY = "AddDeviceInfoDialogFragmentKey"
        const val BUNDLE_KEY_NAME = "BundleKeyName"
        const val BUNDLE_KEY_MAKER = "BundleKeyMaker"
        const val BUNDLE_KEY_OS = "BundleKeyOs"
        const val BUNDLE_KEY_SIZE = "BundleKeySize"
        const val BUNDLE_KEY_DATEMODIFIED = "BundleKeyDateModified"
        const val BUNDLE_KEY_DATEADDED = "BundleKeyDateAdded"
    }

    private var _binding: AddDeviceInfoFragmentBinding? = null
    private val binding get() = _binding!!
    private val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")//uuuu/MM/DD/HH:mm:ss 強制終了

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        _binding = AddDeviceInfoFragmentBinding.inflate(requireActivity().layoutInflater)
        val mDialog = MaterialAlertDialogBuilder(requireContext()).setView(binding.root)
            .setTitle("デバイス情報追加")
            .setPositiveButton("追加"){ _, _ ->
                //ラムダ{}の使わない引数は_
                setFragmentResult(REQUEST_KEY,
                bundleOf(
                    BUNDLE_KEY_NAME to binding.editTextName.text.toString(),
                    BUNDLE_KEY_MAKER to binding.editTextMaker.text.toString(),
                    BUNDLE_KEY_OS to binding.editTextOs.text.toString(),
                    BUNDLE_KEY_SIZE to binding.editTextSize.text.toString(),
                    BUNDLE_KEY_DATEMODIFIED to ZonedDateTime.now().format(formatter),
                    BUNDLE_KEY_DATEADDED to ZonedDateTime.now().format(formatter)
                ))

            }
            .setNegativeButton("キャンセル"){ _, _ ->
                dialog?.cancel()
            }
            .create()

        return mDialog
    }



}