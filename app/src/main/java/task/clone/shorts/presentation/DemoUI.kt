package task.clone.shorts.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import task.clone.shorts.PlayerViewModel
import task.clone.shorts.ShortsViewModel

@Composable
fun Demo(
    shortsViewModel: ShortsViewModel = hiltViewModel(),
    playerViewModel: PlayerViewModel = hiltViewModel()
) {
    val shortsList = shortsViewModel.usersPage.collectAsLazyPagingItems()
    val videoItems by playerViewModel.videoItems.collectAsState()



    LazyColumn(modifier = Modifier.background(Color.White)){
        items(shortsList){item ->
            Row {
                Text(text = item?.postId ?: "")
                Text(text = item?.creator?.name ?: "")
            }

        }
    }

}