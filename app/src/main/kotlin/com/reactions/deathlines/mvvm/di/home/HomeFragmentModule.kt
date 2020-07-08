package com.reactions.deathlines.mvvm.di.home

import com.reactions.deathlines.presentation.ui.albumdetail.AlbumDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.reactions.deathlines.presentation.ui.home.HomeFragment

@Module
abstract class HomeFragmentModule {

    @ContributesAndroidInjector
    abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun AlbumDetailFragment(): AlbumDetailFragment
}