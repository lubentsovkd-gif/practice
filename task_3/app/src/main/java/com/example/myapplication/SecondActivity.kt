package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity() {

    private val TAG = "SecondActivity"
    private lateinit var navController: NavController
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        try {
            // Настраиваем Toolbar (TopBar)
            toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)

            supportActionBar?.apply {
                // Показываем кнопку "Назад"
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }

            // Устанавливаем заголовок
            supportActionBar?.title = "Второй экран"

            // Получаем переданные данные
            val receivedData = intent.getStringExtra("DATA_KEY") ?: "Нет данных"

            // Отображаем полученные данные
            val receivedDataText = findViewById<TextView>(R.id.receivedDataText)
            receivedDataText.text = "Переданные данные: $receivedData"

            Log.d(TAG, "Received data: $receivedData")

            // Настраиваем навигацию после того, как layout полностью загружен
            findViewById<androidx.fragment.app.FragmentContainerView>(R.id.nav_host_fragment).post {
                setupNavigation()
            }

            Log.d(TAG, "SecondActivity created successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error in SecondActivity", e)
            Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupNavigation() {
        try {
            // Получаем NavHostFragment
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController

            // Настраиваем AppBarConfiguration для BottomNavigationView
            val appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.homeFragment,
                    R.id.profileFragment,
                    R.id.settingsFragment
                )
            )



            // Связываем BottomNavigationView с NavController
            val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNav.setupWithNavController(navController)

            Log.d(TAG, "Navigation setup completed successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error setting up navigation", e)
            Toast.makeText(this, "Ошибка настройки навигации: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Обрабатываем нажатие на кнопку "Назад" в TopBar
        when (item.itemId) {
            android.R.id.home -> {
                Log.d(TAG, "Home/Up button clicked")
                // Закрываем SecondActivity и возвращаемся на MainActivity
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Альтернативный метод обработки навигации вверх
        Log.d(TAG, "onSupportNavigateUp called")
        finish()
        return true
    }
}