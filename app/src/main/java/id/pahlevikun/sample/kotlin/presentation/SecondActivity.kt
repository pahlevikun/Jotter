package id.pahlevikun.sample.kotlin.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.pahlevikun.sample.kotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container_fragment
        supportFragmentManager.beginTransaction().add(
            R.id.container_fragment,
            TestFragment()
        )
            .commit()

        button.setOnClickListener { finish() }
    }
}
