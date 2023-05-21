package com.example.armmanager.api

import retrofit2.Response

@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error")
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(
                        body = body,
                        linkHeader = response.headers().get("link")
                    )
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMsg ?: "unknown error")
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(
    val body: T,
//    val links: Map<String, String>
) : ApiResponse<T>() {
    constructor(body: T, linkHeader: String?) : this(
        body = body,
       // links = linkHeader?.extractLinks() ?: emptyMap()
    )

//    val nextPage: Int? by lazy(LazyThreadSafetyMode.NONE) {
//        links[NEXT_LINK]?.let { next ->
//            val matcher = PAGE_PATTERN.matcher(next)
//            if (!matcher.find() || matcher.groupCount() != 1) {
//                null
//            } else {
//                try {
//                    Integer.parseInt(matcher.group(1)!!)
//                } catch (ex: NumberFormatException) {
//                    Timber.w("cannot parse next page from %s", next)
//                    null
//                }
//            }
//        }
//    }
}

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()