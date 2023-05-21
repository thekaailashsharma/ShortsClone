package task.clone.shorts.netework.dataClass


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "offset")
    val offset: String,
    @Json(name = "page")
    val page: String,
    @Json(name = "posts")
    val posts: List<Post>
)