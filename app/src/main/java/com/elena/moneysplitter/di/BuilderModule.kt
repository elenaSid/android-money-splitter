package com.elena.moneysplitter.di

import com.elena.moneysplitter.family.di.FamilyEditModule
import com.elena.moneysplitter.family.di.FamilyEditScope
import com.elena.moneysplitter.family.ui.FamilyEditActivity
import com.elena.moneysplitter.root.di.RootModule
import com.elena.moneysplitter.root.di.RootScope
import com.elena.moneysplitter.root.ui.RootActivity
import com.elena.moneysplitter.users.edit.ui.EditUserActivity
import com.elena.moneysplitter.users.edit.di.UserEditModule
import com.elena.moneysplitter.users.edit.di.UserEditScope
import com.elena.moneysplitter.users.list.di.UserOldScope
import com.elena.moneysplitter.users.list.di.UsersOldModule
import com.elena.moneysplitter.users.list.ui.UserOldFragment
import com.elena.moneysplitter.wizard.di.WizardModule
import com.elena.moneysplitter.wizard.di.WizardScope
import com.elena.moneysplitter.wizard.steps.families.di.FamiliesModule
import com.elena.moneysplitter.wizard.steps.families.di.FamiliesScope
import com.elena.moneysplitter.wizard.steps.families.ui.FamiliesFragment
import com.elena.moneysplitter.wizard.steps.users.di.UsersModule
import com.elena.moneysplitter.wizard.steps.users.di.UsersScope
import com.elena.moneysplitter.wizard.steps.users.ui.UsersFragment
import com.elena.moneysplitter.wizard.ui.WizardActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 19:34
 */
@Module
abstract class BuilderModule {
    @RootScope
    @ContributesAndroidInjector(modules = [(RootModule::class)])
    abstract fun bindSplitterActivity(): RootActivity

    @UserOldScope
    @ContributesAndroidInjector(modules = [(UsersOldModule::class)])
    abstract fun bindUsersOldFragment(): UserOldFragment

    @UserEditScope
    @ContributesAndroidInjector(modules = [(UserEditModule::class)])
    abstract fun bindEditUserActivity(): EditUserActivity

    @WizardScope
    @ContributesAndroidInjector(modules = [WizardModule::class])
    abstract fun bindWizardActivity(): WizardActivity

    @UsersScope
    @ContributesAndroidInjector(modules = [UsersModule::class])
    abstract fun bindUsersFragment(): UsersFragment

    @FamiliesScope
    @ContributesAndroidInjector(modules = [FamiliesModule::class])
    abstract fun bindFamiliesFragment(): FamiliesFragment

    @FamilyEditScope
    @ContributesAndroidInjector(modules = [FamilyEditModule::class])
    abstract fun bindFamilyEditActivity(): FamilyEditActivity
}