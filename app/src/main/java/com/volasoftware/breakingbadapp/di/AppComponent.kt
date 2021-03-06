package com.volasoftware.breakingbadapp.di

import android.app.Application
import com.volasoftware.breakingbadapp.BreakingBadApp
import com.volasoftware.breakingbadapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        DataModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        FragmentsModule::class,
        ActivitiesModule::class]
)
interface AppComponent {

    fun inject(app: BreakingBadApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationBind(app: Application): Builder

        fun build(): AppComponent
    }

}