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
public final class BodyMeasurementsRepositoryImpl_Factory implements Factory<BodyMeasurementsRepositoryImpl> {
  private final Provider<FirebaseAuth> authProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public BodyMeasurementsRepositoryImpl_Factory(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    this.authProvider = authProvider;
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public BodyMeasurementsRepositoryImpl get() {
    return newInstance(authProvider.get(), firestoreProvider.get());
  }

  public static BodyMeasurementsRepositoryImpl_Factory create(Provider<FirebaseAuth> authProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    return new BodyMeasurementsRepositoryImpl_Factory(authProvider, firestoreProvider);
  }

  public static BodyMeasurementsRepositoryImpl newInstance(FirebaseAuth auth,
      FirebaseFirestore firestore) {
    return new BodyMeasurementsRepositoryImpl(auth, firestore);
  }
}
