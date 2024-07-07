package pr.edu.utfpr.fuelaverage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import pr.edu.utfpr.fuelaverage.databinding.ActivityListBinding
import pr.edu.utfpr.fuelaverage.databinding.ActivityMainBinding

class ListActivity : AppCompatActivity() {

    private lateinit var lvFuels: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        lvFuels = findViewById(R.id.lvFuels)

        lvFuels.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = position + 1
            if (selectedItem == 1) {
                intent.putExtra("fuelValue", 12)
            } else {
                intent.putExtra("fuelValue", 9)
            }

            setResult(RESULT_OK, intent)
            finish()
        }
    }
}