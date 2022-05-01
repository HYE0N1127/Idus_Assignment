package kr.hs.dgsw.idus_assignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.hs.dgsw.idus_assignment.R
import kr.hs.dgsw.idus_assignment.databinding.ActivityMainBinding
import kr.hs.dgsw.idus_assignment.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}