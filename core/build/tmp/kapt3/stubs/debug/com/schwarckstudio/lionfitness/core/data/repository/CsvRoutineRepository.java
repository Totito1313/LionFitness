package com.schwarckstudio.lionfitness.core.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000e\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0096@\u00a2\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0016\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00152\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/schwarckstudio/lionfitness/core/data/repository/CsvRoutineRepository;", "Lcom/schwarckstudio/lionfitness/core/data/repository/RoutineRepository;", "csvManager", "Lcom/schwarckstudio/lionfitness/core/data/csv/CsvManager;", "(Lcom/schwarckstudio/lionfitness/core/data/csv/CsvManager;)V", "_routinesFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/schwarckstudio/lionfitness/core/model/Routine;", "fileName", "", "gson", "Lcom/google/gson/Gson;", "getRoutine", "routineId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRoutines", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRoutinesFlow", "Lkotlinx/coroutines/flow/Flow;", "loadRoutines", "", "saveRoutine", "routine", "(Lcom/schwarckstudio/lionfitness/core/model/Routine;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveToCsv", "routines", "core_debug"})
public final class CsvRoutineRepository implements com.schwarckstudio.lionfitness.core.data.repository.RoutineRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.schwarckstudio.lionfitness.core.data.csv.CsvManager csvManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.schwarckstudio.lionfitness.core.model.Routine>> _routinesFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fileName = "routines.csv";
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    @javax.inject.Inject()
    public CsvRoutineRepository(@org.jetbrains.annotations.NotNull()
    com.schwarckstudio.lionfitness.core.data.csv.CsvManager csvManager) {
        super();
    }
    
    private final void loadRoutines() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getRoutines(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.schwarckstudio.lionfitness.core.model.Routine>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.schwarckstudio.lionfitness.core.model.Routine>> getRoutinesFlow() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveRoutine(@org.jetbrains.annotations.NotNull()
    com.schwarckstudio.lionfitness.core.model.Routine routine, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getRoutine(@org.jetbrains.annotations.NotNull()
    java.lang.String routineId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.schwarckstudio.lionfitness.core.model.Routine> $completion) {
        return null;
    }
    
    private final void saveToCsv(java.util.List<com.schwarckstudio.lionfitness.core.model.Routine> routines) {
    }
}