package com.winphyoethu.tpg.features.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.winphyoethu.tpg.core.data.character.paging.CharacterPagingDatasource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(val characterPagingDatasource: CharacterPagingDatasource) :
    ViewModel() {

    val characterFlow = Pager(
        config = PagingConfig(pageSize = 20), pagingSourceFactory = { characterPagingDatasource },
    ).flow.cachedIn(viewModelScope)

}