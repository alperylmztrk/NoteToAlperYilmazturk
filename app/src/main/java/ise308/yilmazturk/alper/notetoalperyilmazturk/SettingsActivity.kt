package ise308.yilmazturk.alper.notetoalperyilmazturk

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch

class SettingsActivity : AppCompatActivity() {

    private var showDividers: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        // A SharedPreferences instance for reading data
        val prefs = getSharedPreferences("Note to self", Context.MODE_PRIVATE)
        showDividers = prefs.getBoolean("dividers", true)
        val switch1 = findViewById<Switch>(R.id.switch1)
        // Set the switch on or off as appropriate
        switch1.isChecked = showDividers

        switch1.setOnCheckedChangeListener { button, isChecked ->
            showDividers = isChecked
        }
    }

    override fun onPause() {
        super.onPause()
        // Save the settings here
        val prefs = getSharedPreferences("Note to self", Context.MODE_PRIVATE)
        // A SharedPreferences.Editor instance for writing data
        val editor = prefs.edit()
        editor.putBoolean("dividers", showDividers)
        // Save all the above data
        editor.apply()
    }
}