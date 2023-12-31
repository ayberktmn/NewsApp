package com.ayberk.newsapp.presentation.details.components

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ayberk.newsapp.R
import com.ayberk.newsapp.domain.model.Article
import com.ayberk.newsapp.domain.model.Source
import com.ayberk.newsapp.navgraph.Route
import com.ayberk.newsapp.presentation.onboarding.Dimens.ArticleImageHeight
import com.ayberk.newsapp.presentation.onboarding.Dimens.MediumPadding1
import com.ayberk.newsapp.presentation.onboarding.Dimens.MediumPadding2
import com.ayberk.newsapp.ui.theme.NewsAppTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null){
                        context.startActivity(it)
                    }
                }
            },

            onShareClick = {
                    Intent(Intent.ACTION_SEND).also {
                        it.putExtra(Intent.EXTRA_TEXT,article.url)
                        it.type = "text/plain"
                        if (it.resolveActivity(context.packageManager)!=null){
                            context.startActivity(it)
                        }
                    }
            },

            onBookmarkClick = { event(DetailsEvent.UpsertDeleteArticle(article)) },
            onBackClick =  navigateUp
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ){
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                
                Spacer(modifier = Modifier.height(MediumPadding1))
                
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
                Spacer(modifier = Modifier.height(MediumPadding2))
                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.body
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview(){
    NewsAppTheme(dynamicColor = false) {
        DetailsScreen(
            article = Article(
                author = "",
                title = "This \$230 Refurbished iPad Comes With Beats Headphones",
                description = "This grade “A” refurbished 7th-generation Apple iPad comes with open-box Beats Flex wireless headphones and a complete set of accessories for \$229.99 (reg. \$299.99). Read more...",
                content = "This grade A refurbished 7th-generation Apple iPad comes with open-box Beats Flex wireless headphones and a complete set of accessories for \$229.99 (reg. \$299.99). \\r\\nGrade-A devices are in near-mint … [+1014 chars]",
                publishedAt = "2023-06-16T22:24:33Z",
                source = Source(
                    id = "", name = "bbc"
                ),
                url = "https://lifehacker.com/this-230-refurbished-ipad-comes-with-beats-headphones-1850986626",
                urlToImage = "https://i.kinja-img.com/image/upload/c_fill,h_675,pg_1,q_80,w_1200/c3e5883b36718931d99b5545bb28c0a1.png"
            ), event = {}
        ) {

        }

    }
}