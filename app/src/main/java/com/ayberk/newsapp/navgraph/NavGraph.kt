package com.ayberk.newsapp.navgraph

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.ayberk.newsapp.presentation.bookmark.BookmarkScreen
import com.ayberk.newsapp.presentation.bookmark.BookmarkViewModel
import com.ayberk.newsapp.presentation.news_navigator.components.NewsNavigator
import com.ayberk.newsapp.presentation.onboarding.OnBoardingScreen
import com.ayberk.newsapp.presentation.onboarding.OnBoardingViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun NavGraph(
    startDestination: String
){
  val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route,
            ){
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }
        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ){
            composable(
                route = Route.NewsNavigationScreen.route
            ){
                    NewsNavigator()
            }
        }
    }
}