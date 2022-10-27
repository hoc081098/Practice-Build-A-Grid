package com.hoc081988.practicebuildagrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hoc081988.practicebuildagrid.ui.theme.PracticeBuildAGridTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PracticeBuildAGridTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          TopicsGridScreen(
            topics = DataSource.topics
          )
        }
      }
    }
  }
}

data class Topic(
  @StringRes val name: Int,
  val numberOfCourses: Int,
  @DrawableRes val image: Int,
)

object DataSource {
  val topics = listOf(
    Topic(R.string.architecture, 58, R.drawable.architecture),
    Topic(R.string.crafts, 121, R.drawable.crafts),
    Topic(R.string.business, 78, R.drawable.business),
    Topic(R.string.culinary, 118, R.drawable.culinary),
    Topic(R.string.design, 423, R.drawable.design),
    Topic(R.string.fashion, 92, R.drawable.fashion),
    Topic(R.string.film, 165, R.drawable.film),
    Topic(R.string.gaming, 164, R.drawable.gaming),
    Topic(R.string.drawing, 326, R.drawable.drawing),
    Topic(R.string.lifestyle, 305, R.drawable.lifestyle),
    Topic(R.string.music, 212, R.drawable.music),
    Topic(R.string.painting, 172, R.drawable.painting),
    Topic(R.string.photography, 321, R.drawable.photography),
    Topic(R.string.tech, 118, R.drawable.tech)
  )
}

@Composable
fun TopicGridItem(
  topic: Topic,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
      .fillMaxWidth()
      .height(68.dp)
      .wrapContentHeight(),
    elevation = CardDefaults.cardElevation(
      defaultElevation = 3.0.dp
    ),
    shape = CardDefaults.elevatedShape,
  ) {
    Row {
      Image(
        modifier = Modifier
          .size(68.dp),
        painter = painterResource(id = topic.image),
        contentDescription = stringResource(id = topic.name),
      )

      Column(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight()
          .padding(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 8.dp
          )
      ) {
        Text(
          modifier = Modifier.fillMaxWidth(),
          text = stringResource(id = topic.name),
          style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
          verticalAlignment = Alignment.CenterVertically
        ) {
          Image(
            painter = painterResource(id = R.drawable.ic_baseline_grain_24),
            contentDescription = "Grain"
          )

          Spacer(modifier = Modifier.width(8.dp))

          Text(
            text = topic.numberOfCourses.toString(),
            style = MaterialTheme.typography.bodySmall
          )
        }
      }
    }
  }
}

@Composable
fun TopicsGridScreen(
  topics: List<Topic>,
  modifier: Modifier = Modifier
) {
  LazyVerticalGrid(
    modifier = modifier,
    columns = GridCells.Fixed(count = 2),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(all = 8.dp)
  ) {
    items(topics) { topic ->
      TopicGridItem(
        topic = topic
      )
    }
  }
}

@Preview(
  widthDp = 220,
  showBackground = true,
)
@Composable
fun TopicGridItemPreview() {
  TopicGridItem(
    topic = DataSource.topics[1]
  )
}


@Preview(
  showBackground = true,
  showSystemUi = true
)
@Composable
fun TopicsGridScreenPreview() {
  TopicsGridScreen(
    topics = DataSource.topics
  )
}