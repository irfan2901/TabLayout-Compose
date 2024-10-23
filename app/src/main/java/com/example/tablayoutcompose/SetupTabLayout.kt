package com.example.tablayoutcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.tablayoutcompose.screens.AboutScreen
import com.example.tablayoutcompose.screens.HomeScreen
import com.example.tablayoutcompose.screens.SettingsScreen
import kotlinx.coroutines.launch

@Composable
fun SetupTabLayout(modifier: Modifier = Modifier) {
    val tabTitles = listOf("Home", "Äbout", "Settings")
    val pageState = rememberPagerState(pageCount = { tabTitles.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TabRow(selectedTabIndex = pageState.currentPage, containerColor = Color.LightGray) {
            tabTitles.forEachIndexed { index, text ->
                Tab(
                    selected = index == pageState.currentPage,
                    onClick = { scope.launch { pageState.animateScrollToPage(index) } },
                    text = { Text(text, color = Color.Black) },
                    modifier = Modifier
                        .background(
                            if (index == pageState.currentPage) Color.Magenta else Color.Transparent,
                            shape = RoundedCornerShape(corner = CornerSize(8.dp))
                        )
                        .fillMaxWidth()
                )
            }
        }
        HorizontalPager(state = pageState) { page ->
            when (page) {
                0 -> HomeScreen(modifier)
                1 -> AboutScreen(modifier)
                2 -> SettingsScreen(modifier)
            }
        }
    }
}