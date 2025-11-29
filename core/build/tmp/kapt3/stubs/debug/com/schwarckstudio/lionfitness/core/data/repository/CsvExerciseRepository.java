package com.schwarckstudio.lionfitness.core.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0013\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\u0016\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u000fH\u0002J\u001c\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u001e\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010\u0011J\b\u0010\u001f\u001a\u00020\u000fH\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/schwarckstudio/lionfitness/core/data/repository/CsvExerciseRepository;", "Lcom/schwarckstudio/lionfitness/core/data/repository/ExerciseRepository;", "csvManager", "Lcom/schwarckstudio/lionfitness/core/data/csv/CsvManager;", "(Lcom/schwarckstudio/lionfitness/core/data/csv/CsvManager;)V", "_exercises", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/schwarckstudio/lionfitness/core/model/Exercise;", "fileName", "", "gson", "Lcom/google/gson/Gson;", "headers", "deleteExercise", "", "exerciseId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExercise", "id", "getExercises", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExercisesFlow", "Lkotlinx/coroutines/flow/Flow;", "loadExercises", "saveExercise", "exercise", "(Lcom/schwarckstudio/lionfitness/core/model/Exercise;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveToCsv", "searchExercises", "query", "seedInitialExercises", "core_debug"})
public final class CsvExerciseRepository implements com.schwarckstudio.lionfitness.core.data.repository.ExerciseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.schwarckstudio.lionfitness.core.data.csv.CsvManager csvManager = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fileName = "exercises.csv";
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> headers = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.schwarckstudio.lionfitness.core.model.Exercise>> _exercises = null;
    
    @javax.inject.Inject()
    public CsvExerciseRepository(@org.jetbrains.annotations.NotNull()
    com.schwarckstudio.lionfitness.core.data.csv.CsvManager csvManager) {
        super();
    }
    
    private final void loadExercises() {
    }
    
    private final void seedInitialExercises() {
    }
    
    private final void saveToCsv() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getExercises(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.schwarckstudio.lionfitness.core.model.Exercise>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.schwarckstudio.lionfitness.core.model.Exercise>> getExercisesFlow() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getExercise(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.schwarckstudio.lionfitness.core.model.Exercise> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveExercise(@org.jetbrains.annotations.NotNull()
    com.schwarckstudio.lionfitness.core.model.Exercise exercise, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteExercise(@org.jetbrains.annotations.NotNull()
    java.lang.String exerciseId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchExercises(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.schwarckstudio.lionfitness.core.model.Exercise>> $completion) {
        return null;
    }
}