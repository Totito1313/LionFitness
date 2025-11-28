package com.schwarckstudio.lionfitness.ui.screens.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schwarckstudio.lionfitness.core.data.repository.AnalyticsRepository
import com.schwarckstudio.lionfitness.core.data.repository.UserStats
import com.schwarckstudio.lionfitness.core.data.repository.WeeklyStats
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class StatisticsUiState(
    val userStats: UserStats? = null,
    val isLoading: Boolean = true
)

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) : ViewModel() {

    val uiState: StateFlow<StatisticsUiState> = analyticsRepository.getUserStats()
        .map { stats ->
            StatisticsUiState(userStats = stats, isLoading = false)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = StatisticsUiState()
        )
}
