package com.example.guesstheword.screens.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentGameBinding


const val SCORE = "score"

class GameFragment : Fragment() {
    // The current word


    lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        viewModel = ViewModelProvider(this)[GameViewModel::class.java]
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getFinishedState().observe(viewLifecycleOwner, Observer { isFinished ->
            if (isFinished) {
                val currentScore = viewModel.getScore().value ?: 0
                val bundle = Bundle()
                bundle.putInt(SCORE, currentScore)
                findNavController().navigate(R.id.action_game_to_score, bundle)
                viewModel.changeFinishedState()
            }
        })
        viewModel.getCurrentTime().observe(viewLifecycleOwner, Observer { newTime ->
            binding.timerText.text = DateUtils.formatElapsedTime(newTime)
        })
        viewModel.buzzType.observe(viewLifecycleOwner, Observer { buzzType ->
            if(buzzType != GameViewModel.BuzzType.NO_BUZZ){
                buzz(buzzType.pattern)
                viewModel.onBuzzComplete()
            }
        })
        return binding.root
    }
    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()
        buzzer?.let {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                buzzer.vibrate(VibrationEffect.createWaveform(pattern,-1))
            }else{
                buzzer.vibrate(pattern,-1)
            }
        }
    }

}