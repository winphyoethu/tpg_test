package com.winphyoethu.tpg.core.data.character.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.winphyoethu.tpg.core.common.result.TpgResult
import com.winphyoethu.tpg.core.model.character.Character
import com.winphyoethu.tpg.domain.character.errorcode.CharacterErrorCode
import com.winphyoethu.tpg.domain.character.repository.CharacterRepository
import javax.inject.Inject

/**
 * Paging Source for Character
 *
 * @param characterRepository [CharacterRepository]
 */
class CharacterPagingDatasource @Inject constructor(
    val characterRepository: CharacterRepository
) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val page = params.key ?: 1

            when(val result = characterRepository.getCharacter(page)) {
                is TpgResult.Success -> {
                    LoadResult.Page(
                        data = result.data.characterList,
                        prevKey = if(page == 1) null else page.minus(1),
                        nextKey = if(result.data.paginationInfo.next == null) null else page.plus(1)
                    )
                }
                is TpgResult.Error ->  {
                    LoadResult.Error(result.e as CharacterErrorCode)
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}