package com.winphyoethu.my_tpg

import android.app.Application
import com.facebook.react.BuildConfig
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.defaults.DefaultReactNativeHost
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TpgApp() : Application(), ReactApplication {

    override val reactNativeHost: ReactNativeHost = object : DefaultReactNativeHost(this) {
        override fun getPackages(): MutableList<ReactPackage> {
            return PackageList(this).packages
        }

        override fun getUseDeveloperSupport(): Boolean {
            return BuildConfig.DEBUG
        }

        override fun getJSMainModuleName(): String = "index"

        override val isNewArchEnabled: Boolean =
            com.winphyoethu.my_tpg.BuildConfig.IS_NEW_ARCHITECTURE_ENABLED

        override val isHermesEnabled: Boolean =
            com.winphyoethu.my_tpg.BuildConfig.IS_HERMES_ENABLED
    }

    override fun onCreate() {
        super.onCreate()
    }

}