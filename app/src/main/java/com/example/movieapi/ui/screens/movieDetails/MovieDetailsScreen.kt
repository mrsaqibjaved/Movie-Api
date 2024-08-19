package com.example.movieapi.ui.screens.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapi.data.model.Result
import com.example.movieapi.ui.sharedComponent.ErrorScreen
import com.example.movieapi.ui.sharedComponent.LoadingScreen
import com.example.movieapi.ui.sharedComponent.MovieImage
import com.example.movieapi.ui.theme.LightCyan
import com.example.movieapi.ui.theme.MovieText
import com.example.movieapi.ui.theme.MovieTitle

@Composable
fun MovieDetailsScreen(movieDetailsResponse: MovieDetailsResponse) {
    val modifier: Modifier = Modifier
        .fillMaxSize()
        .background(LightCyan)
    when (movieDetailsResponse) {
        is MovieDetailsResponse.Success -> MovieDetailsModel(
            modifier = modifier, result = movieDetailsResponse.movieDetails
        )

        MovieDetailsResponse.Error -> ErrorScreen()
        MovieDetailsResponse.Loading -> LoadingScreen()
    }
}

@Composable
fun MovieDetailsModel(modifier: Modifier, result: Result?) {
    if (result != null) {
        Box(
            modifier = modifier, contentAlignment = Alignment.Center
        ) {
            MovieImage(
                modifier = Modifier.fillMaxSize(),
                imageSrc = result.belongsToCollection?.backdropPath
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                MovieImage(
                    modifier = Modifier
                        .width(200.dp)
                        .height(250.dp)
                        .clip(RoundedCornerShape(10.dp)), imageSrc = result.posterPath
                )
                MovieDetailsBottom(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)), result = result)
            }
        }
    }
}

@Composable
fun MovieDetailsBottom(modifier: Modifier, result: Result?) {
    if (result != null) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Cyan),
            elevation = CardDefaults.cardElevation(10.dp)
            ) {
            Column(
                modifier = modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = result.title,
                    style = MovieTitle.copy(fontSize = 20.sp, lineHeight = 22.sp),
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
                MovieDetailsTextRow("Movie Info", "")
                MovieDetailsTextRow("Release Data ", result.date)
                MovieDetailsTextRow("Rating", result.voteAverage.toString())
                MovieDetailsTextRow("Vote count", result.voteCount.toString())
                MovieDetailsTextRow("Overview", result.overview, maxLines = 10)
            }
        }
    }
}

@Composable
fun MovieDetailsTextRow(text: String, description: String, maxLines: Int = 1) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$text: ",
            style = MovieText.copy(color = Color.Blue, fontSize = 16.sp, lineHeight = 18.sp)
        )
        Text(
            text = description,
            style = MovieText.copy(fontSize = 16.sp, lineHeight = 18.sp),
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis
        )
    }
}
