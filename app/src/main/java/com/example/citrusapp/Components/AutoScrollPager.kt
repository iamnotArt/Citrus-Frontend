import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoScrollPager(
    pagerState: PagerState,
    delayMillis: Long = 4000L
) {
    LaunchedEffect(pagerState) {
        while (isActive) {
            delay(delayMillis)
            if (!pagerState.isScrollInProgress) {
                val nextPage = pagerState.currentPage + 1
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }
}
