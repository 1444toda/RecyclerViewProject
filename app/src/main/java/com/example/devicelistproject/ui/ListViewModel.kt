package com.example.devicelistproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.model.DeviceInfoBox
import com.example.repository.DeviceInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository:DeviceInfoRepository
): ViewModel() {

    val deviceInfoList = repository.getAll().asLiveData()


    fun addDeviceInfo(name: String,  maker:String, os: String, size: Float, dateModified: String, dateAdded: String){
        viewModelScope.launch {
            repository.add(
                DeviceInfoBox(
                    0,
                    name,
                    maker,
                    os,
                    size,
                    dateModified,
                    dateAdded
                )
            )
        }

    }

    fun modifiedDeviceInfo(deviceInfoBox: DeviceInfoBox){
        viewModelScope.launch {
            repository.update(deviceInfoBox)
        }
    }

    fun deleteDeviceInfo(deviceInfoBox: DeviceInfoBox){
        viewModelScope.launch{

            repository.remove(deviceInfoBox)
        }
    }



}