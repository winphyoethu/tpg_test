package com.winphyoethu.my_tpg

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.winphyoethu.my_tpg.characterdetail.CHARACTER_ID
import com.winphyoethu.my_tpg.characterdetail.CharacterDetailActivity
import com.winphyoethu.tpg.core.designsystem.ui.theme.TpgTheme
import com.winphyoethu.tpg.features.character.navigation.CharacterListScreen
import com.winphyoethu.tpg.features.character.navigation.characterListScreen
import com.winphyoethu.tpg.features.character.navigation.navigateToSavedCharacterList
import com.winphyoethu.tpg.features.character.navigation.savedCharacterScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TpgTheme {
                val context = LocalContext.current
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = CharacterListScreen,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        characterListScreen(characterClick = { character ->
                            context.startActivity(
                                Intent(context, CharacterDetailActivity::class.java).apply {
                                    putExtra(CHARACTER_ID, character.id)
                                }
                            )
                        }, savedClick = {
                            navController.navigateToSavedCharacterList()
                        })
                        savedCharacterScreen(characterClick = { character ->
                            context.startActivity(
                                Intent(context, CharacterDetailActivity::class.java).apply {
                                    putExtra(CHARACTER_ID, character.id)
                                }
                            )
                        }, backClick = {
                            navController.popBackStack()
                        })
                    }
                }
            }
        }
    }

}