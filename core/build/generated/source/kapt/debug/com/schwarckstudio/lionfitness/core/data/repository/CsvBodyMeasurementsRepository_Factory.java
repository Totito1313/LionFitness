package com.schwarckstudio.lionfitness.core.data.repository;

import com.schwarckstudio.lionfitness.core.data.csv.CsvManager;
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
public final class CsvBodyMeasurementsRepository_Factory implements Factory<CsvBodyMeasurementsRepository> {
  private final Provider<CsvManager> csvManagerProvider;

  public CsvBodyMeasurementsRepository_Factory(Provider<CsvManager> csvManagerProvider) {
    this.csvManagerProvider = csvManagerProvider;
  }

  @Override
  public CsvBodyMeasurementsRepository get() {
    return newInstance(csvManagerProvider.get());
  }

  public static CsvBodyMeasurementsRepository_Factory create(
      Provider<CsvManager> csvManagerProvider) {
    return new CsvBodyMeasurementsRepository_Factory(csvManagerProvider);
  }

  public static CsvBodyMeasurementsRepository newInstance(CsvManager csvManager) {
    return new CsvBodyMeasurementsRepository(csvManager);
  }
}
