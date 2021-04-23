package com.elena.moneysplitter.extras

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.elena.moneysplitter.R

/**
 * Расширение для безопасного запуска фрагментов
 *
 * @author elena
 */
fun Fragment.navigate(destinationId: Int, direction: NavDirections) {
    val navigationController = findNavController()
    if (navigationController.currentDestination?.id != destinationId) {
        navigationController.navigate(direction)
    }
}

/**
 * Расширение для безопасного запуска активностей
 *
 * @author elena
 */
fun AppCompatActivity.navigate(destinationId: Int, direction: NavDirections) {
    val navigationController = findNavController()
    if (navigationController.currentDestination?.id != destinationId) {
        navigationController.navigate(direction)
    }
}

/**
 * Расширение для получения навигационного контроллера для активности
 *
 * @author elena
 */
fun AppCompatActivity.findNavController() =
        Navigation.findNavController(this, R.id.navigation_host_fragment)

/**
 * Расширение для проверки текущего назначения в активности
 *
 * @author elena
 */
fun AppCompatActivity.checkCurrent(destinationId: Int) =
        findNavController().currentDestination?.id == destinationId
