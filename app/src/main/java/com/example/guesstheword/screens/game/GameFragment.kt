package com.example.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentGameBinding
import com.example.guesstheword.screens.game.GameFragmentDirections


const val SCORE = "score"
const val CUR_WORD = "current word"

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
        Log.i("GameFragment", "ViewModelProvider called")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
            updateWordText()
            updateScoreText()
        }
        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
            updateWordText()
            updateScoreText()
        }
        updateScoreText()
        updateWordText()
        return binding.root
    }

    fun gameFinished() {
        val bundle = Bundle()
        bundle.putInt(SCORE, viewModel.score)
        findNavController().navigate(R.id.action_game_to_score, bundle)
    }
    fun updateWordText() {
        binding.wordText.text = viewModel.word

    }
    private fun updateScoreText() {
        binding.scoreText.text = viewModel.score.toString()
    }
}