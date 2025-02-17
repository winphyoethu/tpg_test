package com.winphyoethu.tpg.domain.character.errorcode

import com.winphyoethu.tpg.core.common.result.ErrorCode

/**
 * Error Codes for Character
 *
 * 1. AuthenticationError
 * 2. UnexpectedError
 * 3. UnknownError
 */
sealed class CharacterErrorCode : ErrorCode, Throwable() {

    data class AuthenticationError(override val message: String) : CharacterErrorCode()

    data class UnexpectedError(override val message: String) : CharacterErrorCode()

    data class UnknownError(override val message: String) : CharacterErrorCode()

}

/**
 * Error Codes for Local Character Data Store
 *
 * 1. GetCharacterByIdError
 * 2. SaveCharacterError
 * 3. DeleteCharacterError
 */
sealed class CharacterLocalErrorCode : ErrorCode, Throwable() {

    data class GetCharacterByIdError(override val message: String) : CharacterLocalErrorCode()

    data class SaveCharacterError(override val message: String) : CharacterLocalErrorCode()

    data class DeleteCharacterError(override val message: String) : CharacterLocalErrorCode()

}