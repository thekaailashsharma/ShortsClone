package task.clone.shorts.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import task.clone.shorts.R
import task.clone.shorts.ui.theme.iconColor
import task.clone.shorts.ui.theme.shortsBackground
import kotlin.random.Random

data class AllComments(
    val name: String,
    val comment: String,
    val time: String
)

fun generateAllCommentsList(): List<AllComments> {
    val allCommentsList = mutableListOf<AllComments>()

    repeat(50) {
        val name = generateRandomName()
        val comment = generateRandomComment()
        val time = generateRandomTime()

        val allComments = AllComments(name, comment, time)
        allCommentsList.add(allComments)
    }

    return allCommentsList
}

fun generateRandomName(): String {
    val names = listOf("Ryuk", "Alice", "Bob", "Charlie", "Dave", "Eve", "Frank", "Grace")
    return names.random()
}

fun generateRandomComment(): String {
    val comments = listOf(
        "Hello",
        "Nice video!",
        "Great content!",
        "Awesome work!",
        "I love it!"
    )
    return comments.random()
}

fun generateRandomTime(): String {
    val hours = Random.nextInt(24)
    val minutes = Random.nextInt(60)
    val daysAgo = Random.nextInt(1, 31) // Random number of days ago (1 to 30)
    return "$daysAgo days ago at ${String.format("%02d:%02d", hours, minutes)}"
}

val allCommentsList = generateAllCommentsList()


@Composable
fun Comments() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .background(shortsBackground)
            .fillMaxHeight(0.75f)
    ) {
        var text by remember{
            mutableStateOf("")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Comments",
                    modifier = Modifier
                        .padding(start = 10.dp, end = 3.dp),
                    color = iconColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "8.8K",
                    modifier = Modifier
                        .padding(end = 7.dp),
                    color = iconColor.copy(alpha = 0.4f),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                Icon(
                    Icons.Filled.Sort,
                    contentDescription = "Sorting",
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 10.dp)
                )
                Icon(
                    Icons.Filled.Cancel,
                    contentDescription = "Sorting",
                    tint = iconColor,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 10.dp)
                )
            }

        }
        Divider(
            modifier = Modifier
                .background(Color.LightGray.copy(0.4f))
                .height(1.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(
                imageUrl = R.drawable.pfp,
                modifier = Modifier
                    .size(30.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF82C1FF),
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .clip(CircleShape)
            )

            OutlinedTextField(
                value = text,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                label = { Text(text = "Enter your Comment") },
                placeholder = { Text(text = "Enter your Comment") },
                onValueChange = {
                    text = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }

        Divider(
            modifier = Modifier
                .background(Color.LightGray.copy(0.4f))
                .height(1.dp)
        )

        LazyColumn(){
            items(allCommentsList.size){
                CommentItem(allCommentsList[it])
            }
        }



    }
}

@Composable
fun CommentItem(allComments: AllComments) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            ProfileImage(
                imageUrl = null,
                modifier = Modifier
                    .size(30.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFF82C1FF),
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .clip(CircleShape),
                initial = allComments.name.first()
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Text(
                    text = allComments.name,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = allComments.comment,
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = allComments.time,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }

        Icon(
            painterResource(id = R.drawable.dots),
            contentDescription = "backArrow",
            tint = iconColor,
            modifier = Modifier
                .size(40.dp)
                .padding(10.dp)
        )
    }
}