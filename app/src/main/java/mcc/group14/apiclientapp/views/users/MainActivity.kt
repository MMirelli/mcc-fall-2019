package mcc.group14.apiclientapp.views.users

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.google.firebase.FirebaseApp
import mcc.group14.apiclientapp.R
import mcc.group14.apiclientapp.views.users.LoginActivity
import mcc.group14.apiclientapp.views.projects.dashboard.ProjectsActivity

// Sign-up activity
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init firebase for the entire application
        FirebaseApp.initializeApp(this)

        this.title = "Main"

        val signup_btn = findViewById<Button>(R.id.signup_btn)
        val login_btn = findViewById<Button>(R.id.login_btn)

        // opens login activity
        login_btn.setOnClickListener{
            val intent =
                Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signup_btn.setOnClickListener{
            val intent =
                Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

}

