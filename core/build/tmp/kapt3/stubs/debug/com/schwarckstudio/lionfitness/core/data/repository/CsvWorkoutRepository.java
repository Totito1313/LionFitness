package com.schwarckstudio.lionfitness.core.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0016\u0010\u0017\u001a\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002J\u0016\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u001bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/schwarckstudio/lionfitness/core/data/repository/CsvWorkoutRepository;", "Lcom/schwarckstudio/lionfitness/core/data/repository/WorkoutRepository;", "csvManager", "Lcom/schwarckstudio/lionfitness/core/data/csv/CsvManager;", "(Lcom/schwarckstudio/lionfitness/core/data/csv/CsvManager;)V", "_workoutsFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/schwarckstudio/lionfitness/core/model/WorkoutLog;", "fileName", "", "gson", "Lcom/google/gson/Gson;", "deleteWorkout", "", "workoutId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWorkout", "getWorkouts", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWorkoutsFlow", "Lkotlinx/coroutines/flow/Flow;", "loadWorkouts", "saveToCsv", "workouts", "saveWorkout", "workout", "(Lcom/schwarckstudio/lionfitness/core/model/WorkoutLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "core_debug"})
public final class CsvWorkoutRepository implements com.schwarckstudio.lionfitness.core.data.repository.WorkoutRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.schwarckstudio.lionfitness.core.data.csv.CsvManager csvManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.schwarckstudio.lionfitness.core.model.WorkoutLog>> _workoutsFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fileName = "workouts.csv";
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    @javax.inject.Inject()
    public CsvWorkoutRepository(@org.jetbrains.annotations.NotNull()
    com.schwarckstudio.lionfitness.core.data.csv.CsvManager csvManager) {
        super();
    }
    
    private final void loadWorkouts() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getWorkouts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.schwarckstudio.lionfitness.core.model.WorkoutLog>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.schwarckstudio.lionfitness.core.model.WorkoutLog>> getWorkoutsFlow() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveWorkout(@org.jetbrains.annotations.NotNull()
    com.schwarckstudio.lionfitness.core.model.WorkoutLog workout, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getWorkout(@org.jetbrains.annotations.NotNull()
    java.lang.String workoutId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.schwarckstudio.lionfitness.core.model.WorkoutLog> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteWorkout(@org.jetbrains.annotations.NotNull()
    java.lang.String workoutId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void saveToCsv(java.util.List<com.schwarckstudio.lionfitness.core.model.WorkoutLog> workouts) {
    }
}