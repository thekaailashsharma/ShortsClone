package task.clone.shorts.netework.dataClass


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Video(
    @Json(name = "data")
    val `data`: Data,
    @Json(name = "message")
    val message: String
){
    val name: String
        get() = "$data $message"
}