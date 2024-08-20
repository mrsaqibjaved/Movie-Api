package com.example.movieapi.ui.screens.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movieapi.R
import com.example.movieapi.data.model.Result
import com.example.movieapi.ui.sharedComponent.ErrorScreen
import com.example.movieapi.ui.sharedComponent.LoadingScreen
import com.example.movieapi.ui.sharedComponent.MovieImage
import com.example.movieapi.ui.theme.CardColor
import com.example.movieapi.ui.theme.MovieDetailDescriptionColor
import com.example.movieapi.ui.theme.MovieDetailText
import com.example.movieapi.ui.theme.MovieTitle
import com.example.movieapi.ui.theme.ScreenBackground

@Composable
fun MovieDetailsScreen(movieDetailsResponse: MovieDetailsResponse) {
    val modifier: Modifier = Modifier
        .fillMaxSize()
        .background(ScreenBackground)
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
                MovieDetailsBottom(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp)), result = result
                )
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
            colors = CardDefaults.cardColors(containerColor = CardColor),
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
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    MovieDetailsTextSection(modifier = Modifier.width(IntrinsicSize.Max))
                    MovieDetailsDescriptionSection(modifier = Modifier.fillMaxWidth(1f),
                        result.id.toString(),result.date,result.voteAverage.toString(),result.voteCount.toString(),result.overview)
                }
            }
        }
    }
}

@Composable
fun MovieDetailsTextSection(modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        MovieDetailsText(R.string.movie_info)
        MovieDetailsText(R.string.release_date)
        MovieDetailsText(R.string.rating)
        MovieDetailsText(R.string.vote_count)
        MovieDetailsText(R.string.overview)
    }
}

@Composable
fun MovieDetailsDescriptionSection(
    modifier: Modifier,
    movieInfo: String,
    releaseDate: String,
    rating: String,
    voteCount: String,
    overview: String,
) {
    Column(modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        MovieDetailsDescription(description = movieInfo)
        MovieDetailsDescription(description = releaseDate)
        MovieDetailsDescription(description = rating)
        MovieDetailsDescription(description = voteCount)
        MovieDetailsDescription(description = overview, maxLines = 6)
    }
}

@Composable
fun MovieDetailsDescription(description: String,maxLines: Int = 1) {
    Text(text = description, style = MovieDetailText.copy(color = MovieDetailDescriptionColor), modifier = Modifier.fillMaxWidth(),
        maxLines = maxLines, overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun MovieDetailsText(textResource: Int) {
    Text(
        stringResource(id = textResource)+": ",
        style = MovieDetailText,
        modifier = Modifier.fillMaxWidth()
    )
}
