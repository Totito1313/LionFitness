package com.schwarckstudio.lionfitness.core.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class WorkoutRepositoryImpl_Factory implements Factory<WorkoutRepositoryImpl> {
  private final Provider<FirebaseAuth> authProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public WorkoutRepositoryImpl_Factory(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    this.authProvider = authProvider;
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public WorkoutRepositoryImpl get() {
    return newInstance(authProvider.get(), firestoreProvider.get());
  }

  public static WorkoutRepositoryImpl_Factory create(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    return new WorkoutRepositoryImpl_Factory(authProvider, firestoreProvider);
  }

  public static WorkoutRepositoryImpl newInstance(FirebaseAuth auth, FirebaseFirestore firestore) {
    return new WorkoutRepositoryImpl(auth, firestore);
  }
}
