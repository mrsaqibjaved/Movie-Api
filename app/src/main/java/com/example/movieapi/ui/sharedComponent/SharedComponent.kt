package com.example.movieapi.ui.sharedComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapi.R
import com.example.movieapi.data.DataSource


@Composable
fun LoadingScreen() {
    Image(
        painter = painterResource(id = R.drawable.loading),
        contentDescription = stringResource(id = R.string.loading),
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)
    )
}


@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.connectionerror),
            contentDescription = stringResource(
                id = R.string.failed_to_load
            )
        )
        Text(
            text = stringResource(id = R.string.failed_to_load),
            modifier = Modifier,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun MovieImage(modifier: Modifier, imageSrc: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(DataSource.POSTER_BASE_URL + imageSrc)
            .crossfade(true)
            .error(R.drawable.connectionerror)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(RoundedCornerShape(10.dp))
    )
}