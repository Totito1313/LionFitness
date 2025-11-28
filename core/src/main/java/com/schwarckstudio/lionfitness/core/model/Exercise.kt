package com.schwarckstudio.lionfitness.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Exercise(
    val id: String = "",
    val name: String = "",
    val equipment: Equipment = Equipment.NONE,
    val primaryMuscle: MuscleGroup = MuscleGroup.UNKNOWN,
    val secondaryMuscles: List<MuscleGroup> = emptyList(),
    val type: ExerciseType = ExerciseType.WEIGHT_AND_REPS,
    val imageUrl: String = "",
    val isCustom: Boolean = false,
    val userId: String? = null
) : Parcelable

@Parcelize
enum class MuscleGroup(val displayName: String) : Parcelable {
    // Chest
    CHEST("Pecho"),
    UPPER_CHEST("Pecho Superior"),
    LOWER_CHEST("Pecho Inferior"),
    
    // Back
    BACK("Espalda"),
    LATS("Dorsales"),
    UPPER_BACK("Espalda Alta"),
    LOWER_BACK("Espalda Baja"),
    TRAPS("Trapecios"),
    
    // Shoulders
    SHOULDERS("Hombros"),
    ANTERIOR_DELT("Deltoides Anterior"),
    LATERAL_DELT("Deltoides Lateral"),
    POSTERIOR_DELT("Deltoides Posterior"),
    
    // Arms
    BICEPS("Bíceps"),
    TRICEPS("Tríceps"),
    FOREARMS("Antebrazos"),
    
    // Legs
    LEGS("Piernas"),
    QUADS("Cuádriceps"),
    HAMSTRINGS("Isquiotibiales"),
    GLUTES("Glúteos"),
    CALVES("Pantorrillas"),
    ADDUCTORS("Adductores"),
    ABDUCTORS("Abductores"),
    
    // Core
    ABS("Abdominales"),
    OBLIQUES("Oblicuos"),
    
    // Other
    CARDIO("Cardio"),
    FULL_BODY("Cuerpo Completo"),
    UNKNOWN("Desconocido")
}

@Parcelize
enum class Equipment(val displayName: String) : Parcelable {
    BARBELL("Barra"),
    DUMBBELL("Mancuerna"),
    MACHINE("Máquina"),
    BODYWEIGHT("Peso Corporal"),
    CABLE("Polea"),
    KETTLEBELL("Pesa Rusa"),
    BAND("Banda"),
    NONE("Ninguno"),
    OTHER("Otro")
}

@Parcelize
enum class ExerciseType(val displayName: String, val units: List<String>) : Parcelable {
    WEIGHT_AND_REPS("Peso y Repeticiones", listOf("kg", "reps")),
    BODYWEIGHT_REPS("Peso Corporal Repeticiones", listOf("reps")),
    WEIGHTED_BODYWEIGHT("Peso Corporal Con Peso Añadido", listOf("reps", "+kg")),
    ASSISTED_BODYWEIGHT("Peso Corporal Asistido", listOf("reps", "-kg")),
    DURATION("Duración", listOf("time")),
    DURATION_AND_WEIGHT("Duración y Peso", listOf("kg", "time")),
    DISTANCE_AND_DURATION("Distancia y Duración", listOf("time", "km")),
    WEIGHT_AND_DISTANCE("Peso y Distancia", listOf("kg", "km"))
}
