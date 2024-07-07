package pr.edu.utfpr.fuelaverage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import pr.edu.utfpr.fuelaverage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btListFirstConsumption.setOnClickListener() {
            btListFirstConsumptionOnClick()
        }

        binding.btListSecondConsumption.setOnClickListener() {
            btListSecondConsumptionOnClick()
        }

        binding.btCalculate.setOnClickListener() {
            btCalculateOnClick()
        }

        binding.btClean.setOnClickListener {
            btCleanOnClick()
        }
    }

    private fun btListFirstConsumptionOnClick() {
        val intent = Intent(this, ListActivity::class.java)
        getResultFirstFuel.launch(intent)
    }

    private fun btListSecondConsumptionOnClick() {
        val intent = Intent(this, ListActivity::class.java)
        getResultSecondFuel.launch(intent)
    }

    private val getResultFirstFuel = getResult("first")
    private val getResultSecondFuel = getResult("second")
    private fun getResult(fuelType: String) = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

        if (it.resultCode == RESULT_OK && it.data != null) {
            val result = it.data?.getIntExtra("fuelValue", 12)
            when (fuelType) {
                "first" -> {
                    binding.etFirstFuel.setText(result.toString())
                    binding.etFirstFuel.requestFocus()
                }
                "second" -> {
                    binding.etSecondFuel.setText(result.toString())
                    binding.etSecondFuel.requestFocus()
                }
            }
        }
    }

    private fun btCalculateOnClick() {
        if (binding.etFirstFuel.text.toString().isEmpty()) {
            binding.etFirstFuel.error = getString(R.string.et_first_fuel_error)
            binding.etFirstFuel.requestFocus()
            return
        }

        if (binding.etSecondFuel.text.toString().isEmpty()) {
            binding.etSecondFuel.error = getString(R.string.et_second_fuel_error)
            binding.etSecondFuel.requestFocus()
            return
        }

        if (binding.etFirstValue.text.toString().isEmpty()) {
            binding.etFirstValue.error = getString(R.string.et_first_value_error)
            binding.etFirstValue.requestFocus()
            return
        }

        if (binding.etSecondValue.text.toString().isEmpty()) {
            binding.etSecondValue.error = getString(R.string.et_second_value_error)
            binding.etSecondValue.requestFocus()
            return
        }

        val firstConsumptionValue = binding.etFirstValue.text.toString().toDouble() / binding.etFirstFuel.text.toString().toDouble()
        val secondConsumptionValue = binding.etSecondValue.text.toString().toDouble() / binding.etSecondFuel.text.toString().toDouble()

        if (firstConsumptionValue < secondConsumptionValue) {
            Toast.makeText(this, getString(R.string.first_fuel_most_lucrative), Toast.LENGTH_LONG).show()
        } else if (firstConsumptionValue > secondConsumptionValue) {
            Toast.makeText(this, getString(R.string.second_fuel_most_lucrative), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, getString(R.string.no_difference_values), Toast.LENGTH_LONG).show()
        }
    }

    private fun btCleanOnClick() {
        binding.etFirstFuel.setText("")
        binding.etSecondFuel.setText("")
        binding.etFirstValue.setText("")
        binding.etSecondValue.setText("")
        binding.etFirstFuel.requestFocus()
    }
}