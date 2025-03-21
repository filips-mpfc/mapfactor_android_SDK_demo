package com.example.followtheguidesdk.ui.home

import androidx.lifecycle.ViewModel
import com.example.followtheguidesdk.util.SDKManager
import com.mapfactor.sdk.InitListener
import com.mapfactor.sdk.MpfcEngine.ActivationResult
import com.mapfactor.sdk.MpfcEngine.InitStatus
import com.mapfactor.sdk.MpfcEngine.InitResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sdkManager: SDKManager
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState


    init{
        initSDK()
    }

    private fun initSDK() {
        sdkManager.initSDK(object : InitListener {
            override fun onLocationPermissionNotGranted() {

            }

            override fun onEngineInitStatusChanged(status: InitStatus) {
                _uiState.update {
                    it.copy(
                        sdkInitStatus = status
                    )
                }
            }

            override fun onEngineInitFinished(result: InitResult) {


                if (result == InitResult.SUCCESS) {
                    //HURAYYY, do whatever you want


                } else if (result == InitResult.FAILED_DEVICE_NOT_ACTIVATED) {
                    //oops, activate device

                    sdkManager.activateDevice {
                        activationResult: ActivationResult ->

                        _uiState.update {
                            it.copy(
                                sdkDeviceActivationResult = activationResult
                            )
                        }
                    }


                } else {
                    //catch other cases
                }

                _uiState.update {
                    it.copy(
                        sdkInitResult = result
                    )
                }
            }
        })
    }
}


data class HomeScreenState (
    val sdkInitStatus: InitStatus = InitStatus.NOT_INITIALIZED,
    val sdkInitResult: InitResult = InitResult.NO_RESULT_YET,
    val sdkDeviceActivationResult: ActivationResult? = null
)