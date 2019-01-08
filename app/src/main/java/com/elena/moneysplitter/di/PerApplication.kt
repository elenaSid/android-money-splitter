package com.elena.moneysplitter.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * @author elena
 *         Date: 11.06.2018
 *         Time: 19:29
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
internal annotation class PerApplication