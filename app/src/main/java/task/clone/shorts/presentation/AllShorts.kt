package task.clone.shorts.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import task.clone.shorts.R
import task.clone.shorts.ShortsViewModel
import task.clone.shorts.navigation.Screen
import task.clone.shorts.ui.theme.iconColor
import task.clone.shorts.ui.theme.shortsBackground

@Composable
fun AllShorts(
    shortsViewModel: ShortsViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    var loading by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(shortsBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painterResource(id =R.drawable.youtube),
                    contentDescription = "",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .padding(start = 10.dp, end = 0.dp)
                        .size(25.dp)
                )
                Text(
                    text = "Shorts",
                    color = iconColor,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 7.dp)
                )
            }
            Row {
                Icon(
                    imageVector = Icons.Filled.Cast,
                    contentDescription = "",
                    tint = iconColor,
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(25.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "",
                    tint = iconColor,
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(25.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.dots),
                    contentDescription = "",
                    tint = iconColor,
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(25.dp)
                )
            }

        }

        val shortsList = shortsViewModel.usersPage.collectAsLazyPagingItems()
        if (shortsList.itemCount == 0) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val currenanim by rememberLottieComposition(
                    spec = LottieCompositionSpec.Asset("firstload.json")
                )
                LottieAnimation(
                    composition = currenanim,
                    iterations = Int.MAX_VALUE,
                    contentScale = ContentScale.Crop,
                    speed = 0.75f,
                    modifier = Modifier
                        .size(200.dp)
                )
            }
        } else {


            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(shortsList.itemCount) { index ->
                    AllShortCard(
                        model = shortsList[index]?.submission?.thumbnail,
                        text = shortsList[index]?.creator?.name ?: "",
                        onClick = {
                            navHostController.navigate("${Screen.ShortsUI.route}/${index}")
                            shortsViewModel.pageNumber.value = index
                        }
                    )
                }
                if (loading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.BottomCenter
                        ) {
                            val currenanim by rememberLottieComposition(
                                spec = LottieCompositionSpec.Asset("load.json")
                            )
                            LottieAnimation(
                                composition = currenanim,
                                iterations = Int.MAX_VALUE,
                                contentScale = ContentScale.Crop,
                                speed = 0.75f,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                        }
                    }
                }

            }

            when (shortsList.loadState.append) {
                is LoadState.Error -> {
                    loading = false
                }

                LoadState.Loading -> {
                    loading = true
                }

                is LoadState.NotLoading -> {
                    loading = false
                }
            }


        }
    }
}

@Composable
fun AllShortCard(
    model: Any?,
    text: String,
    onClick: () -> Unit
) {
    Card(colors = CardDefaults.cardColors(
        containerColor = shortsBackground,
    ), shape = RoundedCornerShape(0.dp),
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Box {
            AsyncImage(
                model = model,
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier.border(1.dp, iconColor, RoundedCornerShape(0.dp))
            )
            Text(
                text = text,
                modifier = Modifier.padding(start = 10.dp),
                color = iconColor,
                fontSize = 10.sp
            )

        }

    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )

    }
}