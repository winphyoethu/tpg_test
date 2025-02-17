package com.winphyoethu.my_tpg.characterdetail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.facebook.react.BuildConfig
import com.facebook.react.ReactInstanceEventListener
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactRootView
import com.facebook.react.bridge.ReactContext
import com.facebook.react.common.LifecycleState
import com.facebook.react.shell.MainReactPackage
import com.facebook.soloader.SoLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val CHARACTER_ID = "characterId"

@AndroidEntryPoint
class CharacterDetailActivity : ComponentActivity() {

    val characterDetailViewModel: CharacterDetailViewModel by viewModels()

    private lateinit var reactRootView: ReactRootView
    private lateinit var reactInstanceManager: ReactInstanceManager

    private var characterId: Int = 1

    private var communicationModule: CommunicationModule? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        characterId = intent.getIntExtra(CHARACTER_ID, 1)

        SoLoader.init(this, false)
        reactRootView = ReactRootView(this)

        reactInstanceManager = ReactInstanceManager.builder()
            .setApplication(application)
            .setCurrentActivity(this)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("index")
            .addPackages(listOf(MainReactPackage(), NativeEventPackage()))
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build()

        reactRootView.startReactApplication(reactInstanceManager, "CharacterDetailApp", null)
        setContentView(reactRootView)

        reactInstanceManager.addReactInstanceEventListener(object : ReactInstanceEventListener {
            override fun onReactContextInitialized(context: ReactContext) {
                communicationModule = context.getNativeModule(CommunicationModule::class.java)
                communicationModule?.sendCharacterId(characterId)

                communicationModule?.addCharacterCallback(object : CharacterCallback {
                    override fun characterAction(characterString: String, action: String) {
                        if (action == "save") {
                            characterDetailViewModel.saveCharacter(characterString)
                        } else {
                            characterDetailViewModel.deleteCharacter(characterString)
                        }
                    }
                })

                lifecycleScope.launch {
                    lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        characterDetailViewModel.characterStatus.collectLatest {
                            communicationModule?.sendCharacterStatus(it)
                        }
                    }
                }
            }
        })

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                characterDetailViewModel.characterActionStatus.collectLatest {
                    Toast.makeText(this@CharacterDetailActivity, getString(it), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}