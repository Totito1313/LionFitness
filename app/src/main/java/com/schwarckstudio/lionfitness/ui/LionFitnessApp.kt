package com.schwarckstudio.lionfitness.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.schwarckstudio.lionfitness.core.model.Exercise
import com.schwarckstudio.lionfitness.navigation.Screen
import com.schwarckstudio.lionfitness.ui.screens.exercises.AddExerciseScreen
import com.schwarckstudio.lionfitness.ui.screens.exercises.CreateCustomExerciseScreen
import com.schwarckstudio.lionfitness.ui.screens.exercises.ExerciseDetailScreen
import com.schwarckstudio.lionfitness.ui.screens.exercises.ExerciseListScreen
import com.schwarckstudio.lionfitness.ui.screens.home.HomeScreen
import com.schwarckstudio.lionfitness.ui.screens.onboarding.GetStartedScreen
import com.schwarckstudio.lionfitness.ui.screens.profile.ProfileScreen
import com.schwarckstudio.lionfitness.ui.screens.routines.CreateRoutineScreen
import com.schwarckstudio.lionfitness.ui.screens.routines.MyRoutinesScreen
import com.schwarckstudio.lionfitness.ui.screens.routines.RoutineListScreen
import com.schwarckstudio.lionfitness.ui.screens.stats.StatisticsScreen
import com.schwarckstudio.lionfitness.ui.screens.workout.ActiveWorkoutScreen
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun LionFitnessApp(
    viewModel: MainViewModel = androidx.hilt.navigation.compose.hiltViewModel()
) {
    val navController = rememberNavController()
    val startDestination by viewModel.startDestination.collectAsState()
    
    // Observe finished workout from ActiveWorkoutManager (for watch-initiated finish)
    val finishedWorkout by viewModel.finishedWorkout.collectAsState()

    LaunchedEffect(finishedWorkout) {
        if (finishedWorkout != null) {
            navController.navigate(Screen.WorkoutSummary.route)
            viewModel.clearFinishedWorkout() // We need to clear it so we don't navigate again
        }
    }

    if (startDestination == null) return // Wait for start destination

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route
    
    // Determine if we should show the floating nav bar
    val showBottomBar = currentRoute != Screen.GetStarted.route

    // State for Corner Menu
    var isMenuOpen by remember { mutableStateOf(false) }

    // Persistent TopBar State
    val topBarState = remember { com.schwarckstudio.lionfitness.ui.components.TopBarState() }

    androidx.compose.runtime.CompositionLocalProvider(
        com.schwarckstudio.lionfitness.ui.components.LocalTopBarState provides topBarState
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                // TopBar removed from here to allow content to scroll under it
                bottomBar = {},
                containerColor = Color(0xFFF1F1F3) // Match app background
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = startDestination!!,
                    modifier = Modifier.padding(innerPadding),
                    enterTransition = {
                        fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.95f, animationSpec = tween(300))
                    },
                    exitTransition = {
                        fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 1.05f, animationSpec = tween(300))
                    },
                    popEnterTransition = {
                        fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 1.05f, animationSpec = tween(300))
                    },
                    popExitTransition = {
                        fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.95f, animationSpec = tween(300))
                    }
                ) {
                composable(Screen.GetStarted.route) {
                    GetStartedScreen(
                        onGetStartedClick = {
                            viewModel.completeOnboarding()
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.GetStarted.route) { inclusive = true }
                            }
                        }
                    )
                }
                composable(route = Screen.Home.route) {
                    HomeScreen(
                        onViewHistoryClick = { navController.navigate(Screen.Statistics.route) },
                        onViewRoutinesClick = { navController.navigate(Screen.MyRoutines.route) },
                        onRoutineClick = { routineId ->
                            navController.navigate(Screen.RoutineDetail.createRoute(routineId))
                        }
                    )
                }
                composable(route = Screen.Statistics.route) {
                    StatisticsScreen()
                }
                composable(route = Screen.Routines.route) {
                    val workoutViewModel: com.schwarckstudio.lionfitness.ui.screens.workout.WorkoutViewModel = androidx.hilt.navigation.compose.hiltViewModel()
                    RoutineListScreen(
                        onCreateRoutineClick = { navController.navigate(Screen.CreateRoutine.route) },
                        onRoutineClick = { routine ->
                            navController.navigate(Screen.RoutineDetail.createRoute(routine.id))
                        },
                        onWorkoutClick = { workoutId ->
                            navController.navigate(Screen.WorkoutDetail.createRoute(workoutId))
                        },
                        onNavigateToMyRoutines = {
                            navController.navigate(Screen.MyRoutines.route)
                        }
                    )
                }
                composable(route = Screen.MyRoutines.route) {
                    val workoutViewModel: com.schwarckstudio.lionfitness.ui.screens.workout.WorkoutViewModel = androidx.hilt.navigation.compose.hiltViewModel()
                    MyRoutinesScreen(
                        onNavigateBack = { navController.popBackStack() },
                        onRoutineClick = { routineId ->
                             navController.navigate(Screen.RoutineDetail.createRoute(routineId))
                        },
                        onStartRoutine = { routineId ->
                            navController.navigate(Screen.ActiveWorkout.createRoute(routineId))
                        }
                    )
                }
                composable(route = Screen.Exercises.route) {
                    ExerciseListScreen(
                        onExerciseClick = { exerciseId ->
                            navController.navigate(Screen.ExerciseDetail.createRoute(exerciseId))
                        },
                        onNavigateToCreateCustomExercise = {
                            navController.navigate(Screen.CreateCustomExercise.route)
                        }
                    )
                }
                composable(route = Screen.Profile.route) {
                    ProfileScreen(
                        onNavigateToLogin = {
                            // TODO: Handle navigation to login (e.g., clear backstack)
                        },
                        onNavigateToEditProfile = {
                            navController.navigate(Screen.EditProfile.route)
                        },
                        onNavigateToNotifications = {
                            navController.navigate(Screen.Notifications.route)
                        },
                        onNavigateToPrivacy = {
                            navController.navigate(Screen.PrivacySecurity.route)
                        }
                    )
                }
                composable(Screen.EditProfile.route) {
                    com.schwarckstudio.lionfitness.ui.screens.profile.EditProfileScreen(
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
                composable(Screen.Notifications.route) {
                    com.schwarckstudio.lionfitness.ui.screens.settings.NotificationsScreen(
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
                composable(Screen.PrivacySecurity.route) {
                    com.schwarckstudio.lionfitness.ui.screens.settings.PrivacySecurityScreen(
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
                composable(Screen.CreateRoutine.route) { backStackEntry ->
                    val selectedExercises = backStackEntry.savedStateHandle.get<List<Exercise>>("selected_exercises")
                    
                    CreateRoutineScreen(
                        onNavigateBack = { navController.popBackStack() },
                        onNavigateToAddExercise = { navController.navigate(Screen.AddExercise.route) },
                        newlySelectedExercises = selectedExercises
                    )
                    
                    LaunchedEffect(selectedExercises) {
                        if (selectedExercises != null) {
                            backStackEntry.savedStateHandle.remove<List<Exercise>>("selected_exercises")
                        }
                    }
                }
                
                composable(Screen.AddExercise.route) {
                    ExerciseListScreen(
                        onExerciseClick = { /* Do nothing in selection mode or toggle? Logic is inside screen now */ },
                        onNavigateToCreateCustomExercise = { navController.navigate(Screen.CreateCustomExercise.route) },
                        isSelectionMode = true,
                        onExercisesSelected = { exercises ->
                            navController.previousBackStackEntry?.savedStateHandle?.set("selected_exercises", exercises)
                            navController.popBackStack()
                        }
                    )
                }
                
                composable(Screen.CreateCustomExercise.route) {
                    CreateCustomExerciseScreen(
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
                composable(Screen.ExerciseDetail.route) { backStackEntry ->
                    val exerciseId = backStackEntry.arguments?.getString("exerciseId")
                    if (exerciseId != null) {
                        ExerciseDetailScreen(
                            exerciseId = exerciseId,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
                composable(
                    route = Screen.ActiveWorkout.route,
                    arguments = listOf(androidx.navigation.navArgument("routineId") {
                        nullable = true
                        defaultValue = null
                        type = androidx.navigation.NavType.StringType
                    })
                ) { backStackEntry ->
                    val workoutViewModel: com.schwarckstudio.lionfitness.ui.screens.workout.WorkoutViewModel = androidx.hilt.navigation.compose.hiltViewModel()
                    val routineId = backStackEntry.arguments?.getString("routineId")
                    
                    LaunchedEffect(routineId) {
                        if (routineId != null) {
                            workoutViewModel.startWorkout(routineId)
                        }
                    }
                    
                    val replacementExerciseId = backStackEntry.savedStateHandle.get<String>("replacement_exercise_id")
                    val targetExerciseId = backStackEntry.savedStateHandle.get<String>("replace_target_id")
                    val addExerciseId = backStackEntry.savedStateHandle.get<String>("add_exercise_id")
                    
                    LaunchedEffect(replacementExerciseId, targetExerciseId) {
                        if (replacementExerciseId != null && targetExerciseId != null) {
                            workoutViewModel.replaceExercise(targetExerciseId, replacementExerciseId)
                            backStackEntry.savedStateHandle.remove<String>("replacement_exercise_id")
                            backStackEntry.savedStateHandle.remove<String>("replace_target_id")
                        }
                    }

                    LaunchedEffect(addExerciseId) {
                        if (addExerciseId != null) {
                            workoutViewModel.addExercise(addExerciseId)
                            backStackEntry.savedStateHandle.remove<String>("add_exercise_id")
                        }
                    }

                    ActiveWorkoutScreen(
                        onFinish = { 
                            navController.navigate(Screen.WorkoutSummary.route)
                        },
                        onReplaceExercise = { exerciseId ->
                            backStackEntry.savedStateHandle["replace_target_id"] = exerciseId
                            navController.navigate(Screen.ExercisePicker.route)
                        },
                        onAddExercise = {
                            navController.navigate(Screen.ExercisePicker.route)
                        }
                    )
                }
                
                composable(Screen.WorkoutSummary.route) {
                    com.schwarckstudio.lionfitness.ui.screens.workout.WorkoutSummaryScreen(
                        onNavigateBack = { navController.popBackStack() },
                        onSave = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Home.route) { inclusive = true }
                            }
                        }
                    )
                }
                
                composable(Screen.ExercisePicker.route) {
                    ExerciseListScreen(
                        onExerciseClick = { exerciseId ->
                            // Determine if we are replacing or adding based on previous back stack entry state
                            // Actually, we can just set a generic "selected_exercise" and let the caller decide?
                            // But here we need to distinguish.
                            // Let's check if "replace_target_id" is set in the ActiveWorkout entry.
                            // Accessing previousBackStackEntry might be tricky if we are deep.
                            // But here ExercisePicker is on top of ActiveWorkout.
                            
                            val previousEntry = navController.previousBackStackEntry
                            val replaceTarget = previousEntry?.savedStateHandle?.get<String>("replace_target_id")
                            
                            if (replaceTarget != null) {
                                previousEntry.savedStateHandle.set("replacement_exercise_id", exerciseId)
                            } else {
                                previousEntry?.savedStateHandle?.set("add_exercise_id", exerciseId)
                            }
                            navController.popBackStack()
                        },
                        onNavigateToCreateCustomExercise = {
                            navController.navigate(Screen.CreateCustomExercise.route)
                        }
                    )
                }
                composable(Screen.BodyMeasurements.route) {
                    com.schwarckstudio.lionfitness.ui.screens.measurements.BodyMeasurementsScreen(
                        onNavigateBack = { navController.popBackStack() }
                    )
                }
                
                composable(Screen.RoutineDetail.route) { backStackEntry ->
                    val routineId = backStackEntry.arguments?.getString("routineId")
                    if (routineId != null) {
                        com.schwarckstudio.lionfitness.ui.screens.routines.RoutineDetailScreen(
                            onNavigateBack = { navController.popBackStack() },
                            onStartWorkout = { id ->
                                // Ideally pass routineId to ActiveWorkout
                                navController.navigate(Screen.ActiveWorkout.createRoute(id))
                            }
                        )
                    }
                }
                
                composable(Screen.WorkoutDetail.route) { backStackEntry ->
                    val workoutId = backStackEntry.arguments?.getString("workoutId")
                    if (workoutId != null) {
                        com.schwarckstudio.lionfitness.ui.screens.workout.WorkoutDetailScreen(
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
                composable(Screen.News.route) {
                    com.schwarckstudio.lionfitness.ui.screens.news.NewsScreen()
                }
                composable(Screen.Community.route) {
                    com.schwarckstudio.lionfitness.ui.screens.community.CommunityScreen()
                }
                composable(Screen.Settings.route) {
                    com.schwarckstudio.lionfitness.ui.screens.settings.SettingsScreen()
                }
            }
        }

        // TopBar Overlay
        com.schwarckstudio.lionfitness.ui.components.TopBar(
            state = topBarState,
            modifier = Modifier.align(Alignment.TopCenter)
        )

        // Floating Navigation Bar Overlay
        if (showBottomBar) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp)
            ) {
                com.schwarckstudio.lionfitness.navigation.FloatingNavBar(
                    currentRoute = currentRoute,
                    isMenuOpen = isMenuOpen,
                    onNavigate = { route ->
                        isMenuOpen = false // Close menu on navigation
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    onMenuClick = { isMenuOpen = !isMenuOpen }
                )
            }

            // Corner Menu Overlay
            // Scrim to close menu on outside click
            AnimatedVisibility(
                visible = isMenuOpen,
                enter = fadeIn(animationSpec = tween(200)),
                exit = fadeOut(animationSpec = tween(200))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) { isMenuOpen = false }
                )
            }

            // Position the menu
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 90.dp, end = 20.dp)
            ) {
                AnimatedVisibility(
                    visible = isMenuOpen,
                    enter = scaleIn(
                        animationSpec = tween(300),
                        transformOrigin = TransformOrigin(1f, 1f)
                    ) + fadeIn(animationSpec = tween(300)),
                    exit = scaleOut(
                        animationSpec = tween(250),
                        transformOrigin = TransformOrigin(1f, 1f)
                    ) + fadeOut(animationSpec = tween(250))
                ) {
                    com.schwarckstudio.lionfitness.navigation.CornerMenu(
                        onMenuItemClick = { item ->
                            isMenuOpen = false
                            when (item) {
                                "Perfil" -> {
                                    navController.navigate(Screen.Profile.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                                "Comunidad" -> {
                                    navController.navigate(Screen.Community.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                                "Ejercicios" -> {
                                    navController.navigate(Screen.Exercises.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                                "Noticias" -> {
                                    navController.navigate(Screen.News.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                                "Configuraciones" -> {
                                    navController.navigate(Screen.Settings.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                                else -> {}
                            }
                        }
                    )
                }
            }
        }
    }
    }
}
