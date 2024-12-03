package com.example.guesstheword.screens.score

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentScoreBinding
import com.example.guesstheword.screens.game.GameFragment
import com.example.guesstheword.screens.game.SCORE



class ScoreFragment : Fragment() {
    private lateinit var binding: FragmentScoreBinding
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )
        viewModelFactory = arguments?.getInt(SCORE)?.let { ScoreViewModelFactory(it) }!!
        viewModel = ViewModelProvider(this, viewModelFactory)[ScoreViewModel::class.java]
        binding.playAgainButton.setOnClickListener { view: View ->
            Navigation.findNavController(view).popBackStack()

        }
        viewModel.getScore().observe(viewLifecycleOwner) { f_score ->
            binding.scoreText.text = f_score.toString()
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    private fun getShareIntent(): Intent {
        return ShareCompat.IntentBuilder.from(requireActivity()).setText("You have gotten ${binding.scoreText.text} scores with this app.").setType("text/plain").intent
    }
    private fun shareSucess() {
        startActivity(getShareIntent())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.share -> shareSucess()
        }
        return super.onOptionsItemSelected(item)
    }





}