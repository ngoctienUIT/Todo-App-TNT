package com.ngoctientnt.todoapp.presentation.on_boarding

import androidx.compose.foundation.background
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ngoctientnt.todoapp.R
import com.ngoctientnt.todoapp.presentation.on_boarding.component.BoardingComponent
import com.ngoctientnt.todoapp.presentation.on_boarding.component.FirstBoarding
import com.ngoctientnt.todoapp.ui.theme.TodoAppTheme

@Composable
fun OnBoardingScreen(navController: NavHostController) {
    val pagerState = rememberPagerState { 3 }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    color = colorResource(
                        if (pagerState.currentPage == 0) R.color.lightSeaGreen else R.color.white
                    )
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(state = pagerState) { page ->
                when (page) {
                    0 -> FirstBoarding()
                    1 -> BoardingComponent()
                    2 -> BoardingComponent()
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                (0..2).toList().forEach { index ->
                    Box(
                        modifier = if (index == pagerState.currentPage)
                            Modifier
                                .padding(start = 5.dp)
                                .height(8.dp)
                                .width(25.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(color = colorResource(if (pagerState.currentPage == 0) R.color.water else R.color.lightSeaGreen))
                        else Modifier
                            .padding(start = 5.dp)
                            .size(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(color = colorResource(R.color.water))
                    )
                }
            }
            if (pagerState.currentPage != 0)
                Spacer(modifier = Modifier.weight(1f))
            if (pagerState.currentPage != 0)
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.lightSeaGreen)
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .height(56.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                ) {
                    Text("Continue")
                }
            if (pagerState.currentPage != 0) Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    TodoAppTheme {
        OnBoardingScreen(rememberNavController())
    }
}
