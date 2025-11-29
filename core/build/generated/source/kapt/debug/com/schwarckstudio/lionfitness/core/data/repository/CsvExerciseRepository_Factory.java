package com.schwarckstudio.lionfitness.core.data.repository;

import com.schwarckstudio.lionfitness.core.data.csv.CsvManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class CsvExerciseRepository_Factory implements Factory<CsvExerciseRepository> {
  private final Provider<CsvManager> csvManagerProvider;

  public CsvExerciseRepository_Factory(Provider<CsvManager> csvManagerProvider) {
    this.csvManagerProvider = csvManagerProvider;
  }

  @Override
  public CsvExerciseRepository get() {
    return newInstance(csvManagerProvider.get());
  }

  public static CsvExerciseRepository_Factory create(Provider<CsvManager> csvManagerProvider) {
    return new CsvExerciseRepository_Factory(csvManagerProvider);
  }

  public static CsvExerciseRepository newInstance(CsvManager csvManager) {
    return new CsvExerciseRepository(csvManager);
  }
}
