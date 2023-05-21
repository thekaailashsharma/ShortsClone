package task.clone.shorts.netework.dataClass


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Submission(
    @Json(name = "hyperlink")
    val hyperlink: String,
    @Json(name = "mediaUrl")
    val mediaUrl: String,
    @Json(name = "placeholderUrl")
    val placeholderUrl: String,
    @Json(name = "thumbnail")
    val thumbnail: String
)