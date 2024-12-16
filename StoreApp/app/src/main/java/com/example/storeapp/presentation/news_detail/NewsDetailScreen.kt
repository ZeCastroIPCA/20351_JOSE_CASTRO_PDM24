package com.example.storeapp.presentation.news_detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import com.example.storeapp.domain.model.NewsDetail

@Composable
fun NewsDetailScreen(
    newsDetailViewModel: NewsDetailViewModel, // ViewModel
    newsId: String, // News ID
    onBackPressed: () -> Unit // Back button
) {
    // Fetch news detail as state
    val news by newsDetailViewModel.news.collectAsState()

    // Fetch individual news on launch
    LaunchedEffect(newsId) {
        newsDetailViewModel.fetchIndividualNews(newsId)
    }

    // Display news detail
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "News Insight",
                style = MaterialTheme.typography.titleMedium
            )
        }

        // Display news content or loading screen based on state
        when (val newsDetail = news) {
            null -> LoadingScreen()
            else -> NewsContent(newsDetail)
        }
    }
}

// Loading screen
@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CustomLoadingWheel()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Loading news...")
        }
    }
}

// Custom loading wheel
@Composable
fun CustomLoadingWheel() {
    Canvas(modifier = Modifier.size(50.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        val radius = minOf(canvasWidth, canvasHeight) / 2
        val strokeWidth = 5f

        // Rotating wheel effect
        val angle = (System.currentTimeMillis() / 10) % 360f
        drawCircle(
            color = Color.Gray.copy(alpha = 0.3f),
            radius = radius,
            style = Stroke(width = strokeWidth)
        )
        drawArc(
            color = Color.Blue,
            startAngle = angle,
            sweepAngle = 90f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )
    }
}

// News content
@Composable
fun NewsContent(newsDetail: NewsDetail) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = newsDetail.imageUrl,
            contentDescription = "News Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = newsDetail.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Source: ${newsDetail.source}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                // Delete time from date
                text = newsDetail.date.split("T")[0],
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = newsDetail.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


