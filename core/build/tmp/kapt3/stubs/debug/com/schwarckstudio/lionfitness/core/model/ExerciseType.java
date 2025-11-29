package com.schwarckstudio.lionfitness.core.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0087\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u001d\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u00a2\u0006\u0002\u0010\u0007J\t\u0010\f\u001a\u00020\rH\u00d6\u0001J\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u00d6\u0001R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a\u00a8\u0006\u001b"}, d2 = {"Lcom/schwarckstudio/lionfitness/core/model/ExerciseType;", "", "Landroid/os/Parcelable;", "displayName", "", "units", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/List;)V", "getDisplayName", "()Ljava/lang/String;", "getUnits", "()Ljava/util/List;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "WEIGHT_AND_REPS", "BODYWEIGHT_REPS", "WEIGHTED_BODYWEIGHT", "ASSISTED_BODYWEIGHT", "DURATION", "DURATION_AND_WEIGHT", "DISTANCE_AND_DURATION", "WEIGHT_AND_DISTANCE", "core_debug"})
@kotlinx.parcelize.Parcelize()
public enum ExerciseType implements android.os.Parcelable {
    /*public static final*/ WEIGHT_AND_REPS /* = new WEIGHT_AND_REPS(null, null) */,
    /*public static final*/ BODYWEIGHT_REPS /* = new BODYWEIGHT_REPS(null, null) */,
    /*public static final*/ WEIGHTED_BODYWEIGHT /* = new WEIGHTED_BODYWEIGHT(null, null) */,
    /*public static final*/ ASSISTED_BODYWEIGHT /* = new ASSISTED_BODYWEIGHT(null, null) */,
    /*public static final*/ DURATION /* = new DURATION(null, null) */,
    /*public static final*/ DURATION_AND_WEIGHT /* = new DURATION_AND_WEIGHT(null, null) */,
    /*public static final*/ DISTANCE_AND_DURATION /* = new DISTANCE_AND_DURATION(null, null) */,
    /*public static final*/ WEIGHT_AND_DISTANCE /* = new WEIGHT_AND_DISTANCE(null, null) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String displayName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> units = null;
    
    ExerciseType(java.lang.String displayName, java.util.List<java.lang.String> units) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDisplayName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getUnits() {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.schwarckstudio.lionfitness.core.model.ExerciseType> getEntries() {
        return null;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
}