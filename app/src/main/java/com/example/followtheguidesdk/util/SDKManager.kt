package com.example.followtheguidesdk.util

import android.content.Context
import com.mapfactor.sdk.ActivationListener
import com.mapfactor.sdk.InitListener
import com.mapfactor.sdk.MpfcEngine
import com.mapfactor.sdk.map.MapDataProvider
import com.mapfactor.sdk.map.MapRenderer
import com.mapfactor.sdk.utils.Localization
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SDKManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sdkEngine: MpfcEngine
) {

    fun initSDK(listener: InitListener) {
        safeInit(context, listener)
    }



    private fun safeInit(context: Context, listener: InitListener) {
        if (!MpfcEngine.getInstance().isInitialized) {
            initSDKMap(context, listener)
        } else {
            listener.onEngineInitFinished(MpfcEngine.InitResult.SUCCESS)
        }

    }

    private fun initSDKMap(context: Context, listener: InitListener) {

        // get path
        val sdkDataPath = context.getExternalFilesDir(null).toString()

        // get renderer
        val rendererName: MapRenderer.RendererName = MapRenderer.RendererName.HARDWARE

        // get map data provider
        val mapDataProvider: MapDataProvider.ProviderName = MapDataProvider.ProviderName.OSM

        //get language
        val lang = Localization.Language.ENGLISH_US

        sdkEngine.init(context, sdkDataPath, mapDataProvider, rendererName, lang, listener)
    }


    fun activateDevice(activationListener: ActivationListener) {
        sdkEngine.activateDevice(
            //ask for the key
            "AAAAA-BBBBB-CCCCC-DDDDD-EEEEE"
        , activationListener)
    }




}