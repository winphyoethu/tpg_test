package com.winphyoethu.tpg.core.common.result

/**
 * Base Result Class
 */
sealed class TpgResult<out T> {

    /**
     * Generic Success Result Class
     */
    data class Success<out T>(val data: T) : TpgResult<T>()

    /**
     * Generic Error Result Class
     */
    data class Error<out T>(val e: ErrorCode): TpgResult<T>()

}