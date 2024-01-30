package com.example.feature.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.feature.R
import com.example.feature.model.NewUiModel
import com.example.feature.ui.contract.HomeContract
import com.example.feature.extension.OnBottomReached
import com.example.feature.ui.vm.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (NewUiModel) -> Unit
) {
    if (viewModel.currentState.homeState is HomeContract.HomeState.Idle) {
        viewModel.setEvent(HomeContract.Event.FetchData)
    }
    Scaffold(
        backgroundColor = colorResource(R.color.white),
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(R.color.toolbarColor),
                title = {
                    when (val state = viewModel.uiState.collectAsState().value.homeState) {
                        is HomeContract.HomeState.Success -> {
                            Text(
                                text = state.title,
                                color = colorResource(R.color.toolbarTextColor)
                            )
                        }

                        else -> {}
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.setEvent(HomeContract.Event.OnRefresh) }) {
                        Icon(
                            Icons.Default.Refresh,
                            contentDescription = null,
                            tint = colorResource(R.color.toolbarTextColor)
                        )
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .background(colorResource(R.color.white))
                    .padding(padding)
                    .fillMaxSize(),
            ) {
                DataContent {
                    onItemClick.invoke(it)
                }
            }
        }
    )
}

@Composable
private fun DataContent(
    viewModel: HomeViewModel = hiltViewModel(),
    onItemClick: (NewUiModel) -> Unit
) {
    when (val state = viewModel.uiState.collectAsState().value.homeState) {
        is HomeContract.HomeState.Idle -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white))
            )
        }

        is HomeContract.HomeState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.white))
            ) {
                CircularProgressIndicator(
                    color = colorResource(R.color.toolbarColor),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is HomeContract.HomeState.Empty -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Text(
                    text = stringResource(R.string.empty_list),
                    color = colorResource(R.color.white),
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        is HomeContract.HomeState.Success -> {
            val listState = rememberLazyListState()
            LazyColumn(
                state = listState,
                modifier = Modifier.background(colorResource(R.color.white))
            ) {
                items(state.news.data, key = { it.url }) { model ->
                    NewCardItem(model, onItemClick = { onItemClick.invoke(it) })
                }
            }
            listState.OnBottomReached {
                viewModel.setEvent(HomeContract.Event.LoadMoreData)
            }
        }
    }
}

@Composable
private fun NewCardItem(model: NewUiModel, onItemClick: (NewUiModel) -> Unit) {
    Card(
        backgroundColor = colorResource(R.color.cardViewColor),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                onItemClick.invoke(model)
            }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(model.urlToImage)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.news_header),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(5.dp))
            )
            Text(
                text = model.title,
                color = colorResource(R.color.textColor),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 16.dp),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = model.author,
                color = colorResource(R.color.textColor),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 8.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = model.publishedAt,
                color = colorResource(R.color.textColor),
                modifier = Modifier
                    .padding(top = 8.dp),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
