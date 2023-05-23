package task.clone.shorts.netework.dataClass


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Submission(
    @Json(name = "title")
    val title: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "hyperlink")
    val hyperlink: String?,
    @Json(name = "mediaUrl")
    val mediaUrl: String?,
    @Json(name = "placeholderUrl")
    val placeholderUrl: String?,
    @Json(name = "thumbnail")
    val thumbnail: String?
)