package com.winphyoethu.my_tpg.characterdetail

import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Callback
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.WritableMap
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.modules.core.DeviceEventManagerModule

/**
 * Communication Module to transfer Character Information between two platforms
 */
@ReactModule(name = "CommunicationModule")
class CommunicationModule internal constructor(context: ReactApplicationContext?) :
    ReactContextBaseJavaModule(context) {

    private val CHARACTER_ID = "CHARACTER_ID"
    private val CHARACTER_STATUS = "CHARACTER_STATUS"

    override fun getName(): String = "CommunicationModule"

    private lateinit var characterCallback : CharacterCallback

    fun addCharacterCallback(characterCallback: CharacterCallback) {
        this.characterCallback = characterCallback
    }

    @ReactMethod
    fun sendMessageToAndroid(character: String, action: String, callback: Callback) {
        Log.d("CommunicationModule", "Received message: $character $action")
        characterCallback.characterAction(character, action)

        callback.invoke("Message received in Android: $character")
    }

    fun sendCharacterStatus(isSaved: Boolean) {
        val params = Arguments.createMap().apply {
            putBoolean("characterStatus", isSaved)
        }

        sendEvent(reactApplicationContext, CHARACTER_STATUS, params)
    }

    fun sendCharacterId(characterId: Int) {
        val params = Arguments.createMap().apply {
            putInt("characterId", characterId)
        }

        sendEvent(reactApplicationContext, CHARACTER_ID, params)
    }

    private fun sendEvent(reactContext: ReactContext, eventName: String, params: WritableMap?) {
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(eventName, params)
    }

}

interface CharacterCallback {
    fun characterAction(characterString: String, action: String)
}