package com.schwarckstudio.lionfitness.core.di;

import com.schwarckstudio.lionfitness.core.data.csv.CsvManager;
import com.schwarckstudio.lionfitness.core.data.repository.BodyMeasurementsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class CoreModule_ProvideBodyMeasurementsRepositoryFactory implements Factory<BodyMeasurementsRepository> {
  private final Provider<CsvManager> csvManagerProvider;

  public CoreModule_ProvideBodyMeasurementsRepositoryFactory(
      Provider<CsvManager> csvManagerProvider) {
    this.csvManagerProvider = csvManagerProvider;
  }

  @Override
  public BodyMeasurementsRepository get() {
    return provideBodyMeasurementsRepository(csvManagerProvider.get());
  }

  public static CoreModule_ProvideBodyMeasurementsRepositoryFactory create(
      Provider<CsvManager> csvManagerProvider) {
    return new CoreModule_ProvideBodyMeasurementsRepositoryFactory(csvManagerProvider);
  }

  public static BodyMeasurementsRepository provideBodyMeasurementsRepository(
      CsvManager csvManager) {
    return Preconditions.checkNotNullFromProvides(CoreModule.INSTANCE.provideBodyMeasurementsRepository(csvManager));
  }
}
