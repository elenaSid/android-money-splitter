package com.elena.moneysplitter.splitter.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope

/**
 * @author elena
 *         Date: 12.06.2018
 *         Time: 18:01
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
internal annotation class SplitterScope