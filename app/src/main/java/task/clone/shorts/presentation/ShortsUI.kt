package task.clone.shorts.presentation

import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.RotateLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.launch
import task.clone.shorts.R
import task.clone.shorts.ShortsViewModel
import task.clone.shorts.ui.theme.iconColor
import task.clone.shorts.ui.theme.shortsBackground
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ShortsUI(
    postId: String,
    navController: NavController,
    shortsViewModel: ShortsViewModel = hiltViewModel()
) {

    val shortsList = shortsViewModel.usersPage.collectAsLazyPagingItems()
    val pagerState = rememberPagerState()
    val scaffoldState = rememberBottomSheetScaffoldState()
    var isCommentVisible by remember {
        mutableStateOf(false)
    }
    var came by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit) {
        came = true
    }
    var isLiked by remember {
        mutableStateOf(false)
    }
    var isLikeAnim by remember {
        mutableStateOf(false)
    }
    var isDisliked by remember {
        mutableStateOf(false)
    }
    var isDisLikeAnim by remember {
        mutableStateOf(false)
    }
    var isDescVisible by remember {
        mutableStateOf(false)
    }
    println("Shortslist = ${shortsList.itemSnapshotList}")

    BackHandler {
        navController.popBackStack()
        shortsViewModel.pageNumber.value = 0
    }



    val coroutineScope = rememberCoroutineScope()
    VerticalPager(
        pageCount = shortsList.itemCount, state = pagerState, modifier = Modifier.fillMaxSize()
    ) { page ->


        LaunchedEffect(shortsViewModel.pageNumber) {
            println("Post id is $postId")
            if (pagerState.currentPage == postId.toInt() +1) {
                if (postId != null) {
                    println("PostId is $postId & current page is ${pagerState.currentPage}")
                    pagerState.scrollToPage(0)

                }
            }
        }

        LaunchedEffect(key1 = pagerState) {
            if (isLiked) isLiked = false
            if (isLikeAnim) isLikeAnim = false
            if (isDisliked) isDisliked = false
            if (isDisLikeAnim) isDisLikeAnim = false
        }
        BottomSheetScaffold(
            sheetContent = {
                if (isDescVisible) {
                    DescriptionExpanded(text = shortsList[page]?.submission?.description ?: "")
                }
                if (isCommentVisible) {
                    Comments()
                }
            },
            scaffoldState = scaffoldState,
            sheetShape = RoundedCornerShape(11.dp),
            sheetPeekHeight = 0.dp,
            sheetContainerColor = shortsBackground,
            containerColor = shortsBackground
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Box(modifier = Modifier.fillMaxSize()) {
                    VideoView(
                        videoUri = shortsList[page]?.submission?.mediaUrl ?: "",
                        pagerState = pagerState,
                        onDoubleClick = {
                            isLiked = true
                            isLikeAnim = true
                        })

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Icon(
                                Icons.Filled.Search,
                                contentDescription = "backArrow",
                                tint = iconColor,
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(10.dp)
                            )
                            Icon(
                                Icons.Filled.Camera,
                                contentDescription = "backArrow",
                                tint = iconColor,
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(10.dp)
                            )
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

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.padding(end = 15.dp, bottom = 30.dp)
                        ) {
                            RepeatableIcon(
                                icon = Icons.Filled.ThumbUp,
                                tint = if (shortsList[page]?.reaction?.voted == true || isLiked)
                                    Color(0xFF81C0FF) else iconColor,
                                text = shortsList[page]?.reaction?.count.toString(),
                                isLike = true,
                                likeCall = {
                                    if (!isDisliked) {
                                        isLiked = true
                                        isLikeAnim = true
                                    }
                                    if (isDisliked && !isLiked) {
                                        isDisliked = false
                                        isDisLikeAnim = false
                                        isLiked = true
                                        isLikeAnim = true
                                    }
                                }
                            )

                            RepeatableIcon(
                                icon = Icons.Filled.ThumbDown,
                                text = "Dislike",
                                tint = if (isDisliked)
                                    Color(0xFF81C0FF) else iconColor,
                                isDisLiked = true,
                                disLikedCall = {
                                    if (!isLiked) {
                                        isDisliked = true
                                        isDisLikeAnim = true
                                    }
                                    if (isLiked && !isDisliked) {
                                        isDisliked = true
                                        isDisLikeAnim = true
                                        isLiked = false
                                        isLikeAnim = false
                                    }
                                }
                            )
                            val context = LocalContext.current
                            RepeatableIcon(
                                icon = Icons.Filled.Message,
                                text = shortsList[page]?.comment?.count.toString(),
                                isComment = true,
                                commentCall = {
                                    coroutineScope.launch {
                                        isDescVisible = false
                                        isCommentVisible = true
                                        if (shortsList[page]?.comment?.commentingAllowed == true)
                                            scaffoldState.bottomSheetState.expand()
                                        else {
                                            Toast.makeText(
                                                context,
                                                "Commenting Not Allowed",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                                }
                            )
                            RepeatableIcon(
                                icon = Icons.Filled.Share,
                                text = "Share",
                                isShared = true,
                                shareCall = {
                                    val sendIntent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            shortsList[page]?.submission?.hyperlink ?: ""
                                        )
                                        type = "text/plain"
                                    }
                                    val shareIntent = Intent.createChooser(
                                        sendIntent,
                                        "Share link"
                                    )
                                    context.startActivity(shareIntent)
                                }
                            )
                            RepeatableIcon(
                                icon = Icons.Filled.RotateLeft,
                                text = "Remix",
                            )

                            AsyncImage(
                                model = shortsList[page]?.submission?.placeholderUrl ?: "",
                                contentDescription = "profile",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(7.dp))
                            )

                        }
                    }

                    if (isLikeAnim) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            val currenanim by rememberLottieComposition(
                                spec = LottieCompositionSpec.Asset("like.json")
                            )
                            val progress by animateLottieCompositionAsState(currenanim)
                            LottieAnimation(
                                composition = currenanim,
                                iterations = 2,
                                contentScale = ContentScale.Crop,
                                speed = 0.75f,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                            if (progress == 1.0f) isLikeAnim = false
                        }
                    } else if (isDisLikeAnim) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            val currenanim by rememberLottieComposition(
                                spec = LottieCompositionSpec.Asset("dislike.json")
                            )
                            val progress by animateLottieCompositionAsState(currenanim)
                            LottieAnimation(
                                composition = currenanim,
                                iterations = 2,
                                contentScale = ContentScale.Crop,
                                speed = 0.75f,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                            if (progress == 1.0f) isDisLikeAnim = false
                        }
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, bottom = 30.dp, end = 70.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                ProfileImage(
                                    imageUrl = shortsList[page]?.creator?.pic,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .border(
                                            width = 1.dp,
                                            color = Color.Transparent,
                                            shape = CircleShape
                                        )
                                        .padding(3.dp)
                                        .clip(CircleShape),
                                    initial = shortsList[page]?.creator?.name?.first() ?: '?'
                                )
                                Text(
                                    text = shortsList[page]?.creator?.name ?: "Unknown",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.padding(start = 7.dp, end = 5.dp)
                                )
                                Card(
                                    shape = RoundedCornerShape(5.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.Red.copy(0.85f)
                                    ),
                                ) {
                                    Text(
                                        text = "Subscribe  ",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                            }
                            Text(
                                text = shortsList[page]?.submission?.description
                                    ?: "No Description",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                modifier = Modifier
                                    .padding(start = 7.dp, end = 5.dp)
                                    .clickable {
                                        isDescVisible = true
                                        isCommentVisible = false
                                        coroutineScope.launch {
//                                            if (scaffoldState.bottomSheetState.hasExpandedState && !isDescVisible) {
//                                                scaffoldState.bottomSheetState.hide()
//                                            }
//                                            if (isDescVisible) {
                                            scaffoldState.bottomSheetState.expand()
//                                            }
                                        }
                                    }
                            )


                        }

                    }


                }

            }
        }
    }
}

@Composable
fun RepeatableIcon(
    icon: ImageVector,
    text: String,
    tint: Color = iconColor,
    isLike: Boolean = false,
    isDisLiked: Boolean = false,
    isShared: Boolean = false,
    isComment: Boolean = false,
    commentCall: () -> Unit = {},
    shareCall: () -> Unit = {},
    likeCall: () -> Unit = {},
    disLikedCall: () -> Unit = {},
    modifier: Modifier = Modifier
        .size(40.dp)
        .padding(5.dp)
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Icon(
            icon,
            contentDescription = "backArrow",
            tint = tint,
            modifier = modifier.clickable {
                if (isComment) {
                    commentCall()
                } else if (isLike) {
                    likeCall()
                } else if (isDisLiked) {
                    disLikedCall()
                } else if (isShared) {
                    shareCall()
                }
            },
        )
        Text(
            text = text,
            color = iconColor,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 5.dp, bottom = 7.dp)
        )
    }

}

@Composable
fun DescriptionExpanded(text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier
            .background(shortsBackground)
            .fillMaxHeight(0.25f)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = text,
            color = iconColor,
            fontSize = 16.sp
        )
    }
}
