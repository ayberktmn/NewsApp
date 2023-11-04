package com.ayberk.newsapp.presentation.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ayberk.newsapp.domain.model.Article
import com.ayberk.newsapp.presentation.onboarding.Dimens.ExtraSmallPadding
import com.ayberk.newsapp.presentation.onboarding.Dimens.ExtraSmallPadding2
import com.ayberk.newsapp.presentation.onboarding.Dimens.MediumPadding1

@SuppressLint("SuspiciousIndentation")
@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit

) {
    val handlePagingResult = handlePagingResult(articles = articles)
        if (handlePagingResult){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(MediumPadding1),
                contentPadding = PaddingValues(all = ExtraSmallPadding2)
            ){
                items(count = articles.itemCount){
                    articles[it]?.let {
                        ArticleCard(article = it, onClick = {onClick(it)})
                            
                    }
                }
            }
        }
}


@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>,
) :Boolean {

    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when{
        loadState.refresh is LoadState.Loading ->{
            ShimmerEffect()
            false
        }
        error != null ->{
            EmptyScreen()
            false
        }
        else ->{
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {

    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding1)) {
        repeat(10)  {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MediumPadding1)
            )
        }
    }
}