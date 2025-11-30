package com.schwarckstudio.lionfitness.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.draw.clip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.schwarckstudio.lionfitness.ui.theme.DesignSystem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Local state
    var displayName by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var isInitialized by remember { mutableStateOf(false) }
    
    // Initialize state only once when user data is available
    androidx.compose.runtime.LaunchedEffect(uiState.user, uiState.weight, uiState.height) {
        if (!isInitialized && uiState.user != null) {
            displayName = uiState.user?.displayName ?: ""
            weight = uiState.weight?.toString() ?: ""
            height = uiState.height?.toString() ?: ""
            isInitialized = true
        }
    }
    
    // Local state for image preview to show immediately
    var selectedImageUri by remember { mutableStateOf<android.net.Uri?>(null) }

    val topBarState = com.schwarckstudio.lionfitness.ui.components.LocalTopBarState.current
    androidx.compose.runtime.LaunchedEffect(displayName, weight, height) {
        topBarState.update(
            variant = com.schwarckstudio.lionfitness.ui.components.TopBarVariant.EditRoutine, // Reusing EditRoutine for EditProfile as layout is identical
            title = "Editar Perfil",
            onBackClick = onNavigateBack,
            onActionClick = {
                viewModel.updateProfile(
                    displayName = displayName,
                    weight = weight.toDoubleOrNull(),
                    height = height.toDoubleOrNull()
                )
                onNavigateBack()
            }
        )
    }

    Scaffold(
        containerColor = DesignSystem.Colors.Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(70.dp))
            // Profile Picture Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                val launcher = androidx.activity.compose.rememberLauncherForActivityResult(
                    contract = androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia()
                ) { uri ->
                    if (uri != null) {
                        selectedImageUri = uri
                        viewModel.updateProfilePicture(uri)
                    }
                }

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {
                            launcher.launch(
                                androidx.activity.result.PickVisualMediaRequest(
                                    androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                ) {
                    val imageModel = selectedImageUri ?: uiState.user?.photoUrl
                    
                    if (imageModel == null || (imageModel is String && imageModel.isEmpty())) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(androidx.compose.foundation.shape.CircleShape)
                                .background(DesignSystem.Colors.Primary),
                            contentAlignment = androidx.compose.ui.Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = androidx.compose.ui.graphics.Color.White,
                                modifier = Modifier.size(50.dp)
                            )
                        }
                    } else {
                        com.skydoves.landscapist.coil3.CoilImage(
                            imageModel = { imageModel },
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(androidx.compose.foundation.shape.CircleShape),
                            imageOptions = com.skydoves.landscapist.ImageOptions(
                                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                                alignment = androidx.compose.ui.Alignment.Center
                            )
                        )
                    }
                    
                    // Edit Badge
                    Box(
                        modifier = Modifier
                            .align(androidx.compose.ui.Alignment.BottomEnd)
                            .size(30.dp)
                            .clip(androidx.compose.foundation.shape.CircleShape)
                            .background(androidx.compose.ui.graphics.Color.White)
                            .border(2.dp, DesignSystem.Colors.Background, androidx.compose.foundation.shape.CircleShape),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                            tint = DesignSystem.Colors.Primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            OutlinedTextField(
                value = displayName,
                onValueChange = { displayName = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                shape = DesignSystem.Shapes.Input,
                colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                    focusedTextColor = DesignSystem.Colors.TextPrimary,
                    unfocusedTextColor = DesignSystem.Colors.TextPrimary,
                    focusedLabelColor = DesignSystem.Colors.Primary,
                    unfocusedLabelColor = DesignSystem.Colors.TextSecondary
                )
            )
            
            // Read-only Email Field
            OutlinedTextField(
                value = uiState.user?.email ?: "",
                onValueChange = { },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                readOnly = true,
                shape = DesignSystem.Shapes.Input,
                colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                    focusedTextColor = DesignSystem.Colors.TextPrimary,
                    unfocusedTextColor = DesignSystem.Colors.TextPrimary,
                    focusedLabelColor = DesignSystem.Colors.Primary,
                    unfocusedLabelColor = DesignSystem.Colors.TextSecondary,
                    disabledTextColor = DesignSystem.Colors.TextPrimary,
                    disabledLabelColor = DesignSystem.Colors.TextSecondary
                )
            )
            
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Peso (kg)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = DesignSystem.Shapes.Input,
                colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                    focusedTextColor = DesignSystem.Colors.TextPrimary,
                    unfocusedTextColor = DesignSystem.Colors.TextPrimary,
                    focusedLabelColor = DesignSystem.Colors.Primary,
                    unfocusedLabelColor = DesignSystem.Colors.TextSecondary
                )
            )
            
            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Altura (cm)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = DesignSystem.Shapes.Input,
                colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                    focusedTextColor = DesignSystem.Colors.TextPrimary,
                    unfocusedTextColor = DesignSystem.Colors.TextPrimary,
                    focusedLabelColor = DesignSystem.Colors.Primary,
                    unfocusedLabelColor = DesignSystem.Colors.TextSecondary
                )
            )
        }
    }
}
