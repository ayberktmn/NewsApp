package com.ayberk.newsapp.navgraph

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import com.ayberk.newsapp.presentation.home.HomeScreen
import com.ayberk.newsapp.presentation.home.HomeViewModel
import com.ayberk.newsapp.presentation.onboarding.OnBoardingScreen
import com.ayberk.newsapp.presentation.onboarding.OnBoardingViewModel
import com.ayberk.newsapp.presentation.search.SearchScreen
import com.ayberk.newsapp.presentation.search.SearchViewModel

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
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(state = viewModel.state.value, event = viewModel::onEvent, navigate = {})
            }
        }
    }
}