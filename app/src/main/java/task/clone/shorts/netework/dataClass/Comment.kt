package task.clone.shorts.netework.dataClass


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comment(
    @Json(name = "commentingAllowed")
    val commentingAllowed: Boolean,
    @Json(name = "count")
    val count: Int
)