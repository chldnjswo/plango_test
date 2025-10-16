package com.example.plango_nickname

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.plango_nickname.databinding.ActivityMainBinding
import com.example.plango_nickname.name.NameViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val viewModel  : NameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener {
            val name = binding.nicknameInput.text.toString()
            if(name.isEmpty()){
                Toast.makeText(this, "사용하실 이름을 입력해주세요!", Toast.LENGTH_SHORT).show()

            }
            else{
                viewModel.sendName(name)
            }
        }
        lifecycleScope.launch {
            viewModel.displayName.collectLatest { message->
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()

            }
        }


    }
}