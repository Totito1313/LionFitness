package com.schwarckstudio.lionfitness.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class TopBarMenuItem(
    val text: String,
    val onClick: () -> Unit,
    val color: Color = Color.Black
)

@Stable
class TopBarState {
    var variant by mutableStateOf(TopBarVariant.Home)
    var title by mutableStateOf("")
    var subtitle by mutableStateOf<String?>(null)
    var showUserSubtitle by mutableStateOf(false)
    var userName by mutableStateOf("Alan")
    var duration by mutableStateOf("00:00")
    var heartRate by mutableStateOf("0")
    var onBackClick by mutableStateOf<() -> Unit>({})
    var onMenuClick by mutableStateOf<() -> Unit>({})
    var onActionClick by mutableStateOf<() -> Unit>({})
    var isVisible by mutableStateOf(true)
    var menuItems by mutableStateOf<List<TopBarMenuItem>>(emptyList())
    var rightIcon by mutableStateOf<Int?>(null)

    var profilePicture by mutableStateOf<Any?>(null)

    fun update(
        variant: TopBarVariant,
        title: String = "",
        subtitle: String? = null,
        showUserSubtitle: Boolean = false,
        userName: String = "Alan",
        duration: String = "00:00",
        heartRate: String = "0",
        onBackClick: () -> Unit = {},
        onMenuClick: () -> Unit = {},
        onActionClick: () -> Unit = {},
        isVisible: Boolean = true,
        menuItems: List<TopBarMenuItem> = emptyList(),
        rightIcon: Int? = null,
        profilePicture: Any? = null
    ) {
        this.variant = variant
        this.title = title
        this.subtitle = subtitle
        this.showUserSubtitle = showUserSubtitle
        this.userName = userName
        this.duration = duration
        this.heartRate = heartRate
        this.onBackClick = onBackClick
        this.onMenuClick = onMenuClick
        this.onActionClick = onActionClick
        this.isVisible = isVisible
        this.menuItems = menuItems
        this.rightIcon = rightIcon
        this.profilePicture = profilePicture
    }

}


val LocalTopBarState = staticCompositionLocalOf<TopBarState> {
    error("No TopBarState provided")
}

@Composable
fun SafeTopBarUpdate(
    updateBlock: TopBarState.() -> Unit
) {
    val topBarState = LocalTopBarState.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current
    
    // Use a key to force re-evaluation if dependencies change, 
    // but primarily we rely on the lifecycle event
    DisposableEffect(lifecycleOwner, topBarState) {
        val observer = androidx.lifecycle.LifecycleEventObserver { _, event ->
            if (event == androidx.lifecycle.Lifecycle.Event.ON_RESUME) {
                topBarState.updateBlock()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        
        // Also update immediately if already resumed (e.g. initial composition)
        if (lifecycleOwner.lifecycle.currentState.isAtLeast(androidx.lifecycle.Lifecycle.State.RESUMED)) {
            topBarState.updateBlock()
        }

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
