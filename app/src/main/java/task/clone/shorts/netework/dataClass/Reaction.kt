package task.clone.shorts.netework.dataClass


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Reaction(
    @Json(name = "count")
    val count: Int,
    @Json(name = "voted")
    val voted: Boolean
)