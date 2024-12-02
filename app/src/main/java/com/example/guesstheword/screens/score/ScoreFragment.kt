package com.example.guesstheword.screens.score

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentScoreBinding =  DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )

        // Get args using by navArgs property delegate
        val scoreFragmentArgs = ScoreFragmentArgs.fromBundle(requireArguments())
        binding.scoreText.text = scoreFragmentArgs.score.toString()
        binding.playAgainButton.setOnClickListener { onPlayAgain() }
        setHasOptionsMenu(true)
        return binding.root
    }
    private fun getShareIntent(): Intent {
        return ShareCompat.IntentBuilder.from(requireActivity()).setText(getString(R.string.about_text)).setType("text/plain").intent
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
    private fun onPlayAgain() {
        findNavController().navigate(ScoreFragmentDirections.actionRestart())
    }


}