package org.isaaccode.calculategame.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.isaaccode.calculategame.resources.Theme.Companion.currentTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun ScoresComponent(viewModel: TaskViewModel) {

        val lazyColumnListState = rememberLazyListState()
        val coroutineScope = rememberCoroutineScope()
        val scoreList by viewModel.resultsState.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(currentTheme.colors.primaryBackgroundColor)
        ) {

            LaunchedEffect(scoreList.size) {
                lazyColumnListState.animateScrollToItem(lazyColumnListState.layoutInfo.totalItemsCount)
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(currentTheme.colors.primaryBackgroundColor),
                state = lazyColumnListState
            ) {
                coroutineScope.launch {
                    if (scoreList.size == 10) {
                        lazyColumnListState.scrollToItem(scoreList.size - 1)
                    }
                }

                items(scoreList.size) { index ->
                    ScoreComponent(ScoreComponentOptions.Regular, scoreList[index])
                }
            }
        }
}
