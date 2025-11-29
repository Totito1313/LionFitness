package com.schwarckstudio.lionfitness.core.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/schwarckstudio/lionfitness/core/data/repository/AnalyticsRepositoryImpl;", "Lcom/schwarckstudio/lionfitness/core/data/repository/AnalyticsRepository;", "workoutRepository", "Lcom/schwarckstudio/lionfitness/core/data/repository/WorkoutRepository;", "(Lcom/schwarckstudio/lionfitness/core/data/repository/WorkoutRepository;)V", "calculateGlobalRecords", "Lcom/schwarckstudio/lionfitness/core/data/repository/GlobalRecords;", "workouts", "", "Lcom/schwarckstudio/lionfitness/core/model/WorkoutLog;", "calculateUserStats", "Lcom/schwarckstudio/lionfitness/core/data/repository/UserStats;", "getGlobalRecords", "Lkotlinx/coroutines/flow/Flow;", "getUserStats", "core_debug"})
public final class AnalyticsRepositoryImpl implements com.schwarckstudio.lionfitness.core.data.repository.AnalyticsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepository workoutRepository = null;
    
    @javax.inject.Inject()
    public AnalyticsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepository workoutRepository) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.schwarckstudio.lionfitness.core.data.repository.UserStats> getUserStats() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.schwarckstudio.lionfitness.core.data.repository.GlobalRecords> getGlobalRecords() {
        return null;
    }
    
    private final com.schwarckstudio.lionfitness.core.data.repository.GlobalRecords calculateGlobalRecords(java.util.List<com.schwarckstudio.lionfitness.core.model.WorkoutLog> workouts) {
        return null;
    }
    
    private final com.schwarckstudio.lionfitness.core.data.repository.UserStats calculateUserStats(java.util.List<com.schwarckstudio.lionfitness.core.model.WorkoutLog> workouts) {
        return null;
    }
}