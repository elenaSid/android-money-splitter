package com.elena.moneysplitter.wizard.di

import com.elena.domain.family.FamilyRepository
import com.elena.domain.item.ItemRepository
import com.elena.domain.user.UserRepository
import com.elena.domain.user.interaction.RemoveAllDataUseCase
import com.elena.moneysplitter.R
import com.elena.moneysplitter.navigation.FragmentNavigator
import com.elena.moneysplitter.navigation.NavigationLifecycleObserver
import com.elena.moneysplitter.wizard.mvp.WizardPresenter
import com.elena.moneysplitter.wizard.ui.WizardActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides

/**
 * @author elena
 */
@Module
class WizardModule {

    @Provides
    @WizardScope
    fun provideNavigator(activity: WizardActivity) =
            FragmentNavigator(activity, R.id.navigationHost)

    @Provides
    @WizardScope
    fun provideNavigationLifecycleObserver(
            navigator: FragmentNavigator,
            navigatorHolder: NavigatorHolder
    ) = NavigationLifecycleObserver(navigator, navigatorHolder)

    @Provides
    @WizardScope
    fun provideRemoveAllDataUseCase(
            familyRepository: FamilyRepository,
            itemRepository: ItemRepository,
            userRepository: UserRepository,
    ) = RemoveAllDataUseCase(familyRepository, itemRepository, userRepository)

    @Provides
    @WizardScope
    fun provideWizardPresenter(
            removeAllDataUseCase: RemoveAllDataUseCase,
            router: Router
    ) = WizardPresenter(removeAllDataUseCase, router)
}