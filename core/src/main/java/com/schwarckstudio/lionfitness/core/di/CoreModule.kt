package com.schwarckstudio.lionfitness.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.schwarckstudio.lionfitness.core.data.csv.CsvManager
import com.schwarckstudio.lionfitness.core.data.repository.CsvExerciseRepository
import com.schwarckstudio.lionfitness.core.data.repository.ExerciseRepository
import com.schwarckstudio.lionfitness.core.data.repository.RoutineRepository
import com.schwarckstudio.lionfitness.core.data.repository.RoutineRepositoryImpl
import com.schwarckstudio.lionfitness.core.data.repository.UserRepository
import com.schwarckstudio.lionfitness.core.data.repository.UserRepositoryImpl
import com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepository
import com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepositoryImpl
import com.schwarckstudio.lionfitness.core.data.repository.AnalyticsRepository
import com.schwarckstudio.lionfitness.core.data.repository.AnalyticsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideUserRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): UserRepository {
        return UserRepositoryImpl(auth, firestore)
    }



    @Provides
    @Singleton
    fun provideExerciseRepository(
        csvManager: CsvManager
    ): ExerciseRepository {
        return CsvExerciseRepository(csvManager)
    }

    @Provides
    @Singleton
    fun provideWorkoutRepository(
        csvManager: com.schwarckstudio.lionfitness.core.data.csv.CsvManager
    ): com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepository {
        return com.schwarckstudio.lionfitness.core.data.repository.CsvWorkoutRepository(csvManager)
    }

    @Provides
    @Singleton
    fun provideRoutineRepository(
        csvManager: com.schwarckstudio.lionfitness.core.data.csv.CsvManager
    ): com.schwarckstudio.lionfitness.core.data.repository.RoutineRepository {
        return com.schwarckstudio.lionfitness.core.data.repository.CsvRoutineRepository(csvManager)
    }

    @Provides
    @Singleton
    fun provideAnalyticsRepository(
        workoutRepository: WorkoutRepository
    ): AnalyticsRepository {
        return AnalyticsRepositoryImpl(workoutRepository)
    }

    @Provides
    @Singleton
    fun provideBodyMeasurementsRepository(
        csvManager: com.schwarckstudio.lionfitness.core.data.csv.CsvManager
    ): com.schwarckstudio.lionfitness.core.data.repository.BodyMeasurementsRepository {
        return com.schwarckstudio.lionfitness.core.data.repository.CsvBodyMeasurementsRepository(csvManager)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(
        @dagger.hilt.android.qualifiers.ApplicationContext context: android.content.Context
    ): com.schwarckstudio.lionfitness.core.data.repository.SettingsRepository {
        return com.schwarckstudio.lionfitness.core.data.repository.SettingsRepositoryImpl(context)
    }
}
