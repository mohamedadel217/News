package com.example.feature.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.feature.R
import com.example.feature.model.NewUiModel

@Composable
fun DetailScreen(
    newUiModel: NewUiModel,
    onBackClicked: () -> Unit
) {
    Scaffold(
        backgroundColor = colorResource(R.color.brown),
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClicked.invoke()
                    }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = colorResource(R.color.toolbarTextColor)
                        )
                    }
                },
                backgroundColor = colorResource(R.color.toolbarColor),
                title = {
                    Text(
                        text = newUiModel.sourceUiModel.name,
                        color = colorResource(R.color.toolbarTextColor)
                    )
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
                    .fillMaxSize(),
            ) {
                NewItem(model = newUiModel)
            }
        }
    )
}

@Composable
private fun NewItem(model: NewUiModel) {
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
            modifier = Modifier
                .padding(top = 16.dp),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = model.author,
            color = colorResource(R.color.textColor),
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
        Text(
            text = model.description,
            color = colorResource(R.color.textColor),
            modifier = Modifier
                .padding(top = 8.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = model.content,
            color = colorResource(R.color.textColor),
            modifier = Modifier
                .padding(top = 8.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}