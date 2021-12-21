package com.example.devicelistproject.widget

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import com.example.devicelistproject.R
import com.example.devicelistproject.databinding.LayoutDeviceInfoItemBinding
import com.example.devicelistproject.databinding.ListFragmentBinding
import com.example.devicelistproject.ui.ListFragment
import com.example.devicelistproject.ui.MainActivity
import com.example.model.DeviceInfoBox
import com.xwray.groupie.viewbinding.BindableItem
import java.security.AccessController.getContext
import kotlin.coroutines.coroutineContext

data class DeviceInfoItem(val deviceInfoItem: DeviceInfoBox, val onClick: (id: Int) -> Unit, val onDelete: (deviceInfoItem: DeviceInfoBox) -> Unit,val onModified: (deviceInfoItem: DeviceInfoBox) -> Unit,val onMenu: () -> Unit ): BindableItem<LayoutDeviceInfoItemBinding>(){
    override fun bind(viewBinding: LayoutDeviceInfoItemBinding, position: Int) {
        viewBinding.name.text = deviceInfoItem.name
        ("メーカー：" + deviceInfoItem.maker).also { viewBinding.maker.text = it }
        ("OS：" + deviceInfoItem.os).also { viewBinding.deviceOs.text = it }
        ("画面サイズ：" + deviceInfoItem.displaySize).also { viewBinding.deviceSize.text = it }
        ("更新日時：" + deviceInfoItem.dateModified).also { viewBinding.dateModified.text = it }
        ("追加日時：" + deviceInfoItem.dateAdded).also { viewBinding.dateAdded.text = it }
        viewBinding.root.setOnClickListener{
            onClick(deviceInfoItem.id)
        }

        viewBinding.root.setOnClickListener {
            onModified(deviceInfoItem)
        }

        viewBinding.deleteButton.setOnClickListener {
            onDelete(deviceInfoItem)
        }




    }







    override fun getLayout(): Int { //リソースIDを返す
        return R.layout.layout_device_info_item

    }

    override fun initializeViewBinding(viewBinding: View): LayoutDeviceInfoItemBinding {
        return LayoutDeviceInfoItemBinding.bind(viewBinding)
    }

}