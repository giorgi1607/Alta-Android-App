package giorgi.gt.altaapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var  navhost:NavController
    lateinit var bottom_nav_view: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navhost = findNavController(R.id.navhost)

        bottom_nav_view = findViewById(R.id.bottom_navigation_view)



        bottom_nav_view.setupWithNavController( navhost)

















    }




}