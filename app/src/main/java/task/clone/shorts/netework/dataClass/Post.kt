package task.clone.shorts.netework.dataClass


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Post(
    @Json(name = "comment")
    val comment: Comment,
    @Json(name = "creator")
    val creator: Creator,
    @Json(name = "postId")
    val postId: String,
    @Json(name = "reaction")
    val reaction: Reaction,
    @Json(name = "submission")
    val submission: Submission
)