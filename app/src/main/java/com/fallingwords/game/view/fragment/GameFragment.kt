package com.fallingwords.game.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.fallingwords.R
import com.fallingwords.databinding.FragmentGameBinding
import com.fallingwords.game.viewmodel.GameViewModel
import com.fallingwords.home.model.datamodel.Word
import com.fallingwords.utils.FallingWordsAppConstants.CONVERT_MILLIS
import com.fallingwords.utils.FallingWordsAppConstants.DEFAULT_INT_VALUE
import com.fallingwords.utils.FallingWordsAppConstants.TIME_INTERVAL
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.uzairiqbal.circulartimerview.CircularTimerListener
import com.uzairiqbal.circulartimerview.TimeFormatEnum
import kotlin.math.ceil

/** The fragment shows the UI of Game screen **/
class GameFragment : Fragment() {

    /** This variable will store the instance of GameFragment layout file views **/
    private lateinit var binding: FragmentGameBinding

    /** This variable will store the words passed from previous screen **/
    private lateinit var words: List<Word>

    /** This variable will store the instance of GameViewModel **/
    private val gameViewModel: GameViewModel by viewModels()

    /** This variable will store the values of GameFragmentArgs passed from previous screen **/
    private val gameFragmentArgs by navArgs<GameFragmentArgs>()

    /** This variable will store the count of answers that were correct by the user **/
    private var finalScore = DEFAULT_INT_VALUE

    /** This function will create the view of the layout file defined **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(inflater)
        return binding.root
    }

    /** This function will be called after the view is created and used to initialize the views and values **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    /** This function will start calling the functions to initialize views **/
    private fun initViews() {
        setData()
        setListeners()
        observeLiveData()
        fetchWord()
    }

    /** This function will set the data to the variables from arguments passed from previous screen **/
    private fun setData() {
        words = gameFragmentArgs.words.toList()
        gameViewModel.setWords(words)
    }

    /** This function will set the listener for the views used **/
    private fun setListeners() {
        binding.ivIncorrect.setOnClickListener {
            updateFinalScore(false)
        }

        binding.ivCorrect.setOnClickListener {
            updateFinalScore(true)
        }
    }

    /** This function will update the final score if the answer is correct and will fetch the next word **/
    private fun updateFinalScore(bool: Boolean) {
        if (gameViewModel.word.value?.isCorrectTranslation == bool) finalScore++
        binding.tvScoreValue.text = finalScore.toString()
        gameViewModel.nextWord()
    }

    /** This function will observe the LiveData called in the ViewModel **/
    private fun observeLiveData() {
        gameViewModel.word.observe(viewLifecycleOwner) {
            binding.tvEnglishWord.text = it.englishText

            binding.tvSpanishWord.apply {
                text = it.spanishText
                startAnimation(AnimationUtils.loadAnimation(context, R.anim.falling))
            }

            binding.tvTimeRemaining.apply {
                setCircularTimerListener(object : CircularTimerListener {
                    override fun updateDataOnTick(remainingTimeInMs: Long): String =
                        ceil(remainingTimeInMs.div(CONVERT_MILLIS.toDouble())).toInt().toString()

                    override fun onTimerFinished() {

                    }
                }, TIME_INTERVAL, TimeFormatEnum.SECONDS)
                startTimer()
            }
        }

        gameViewModel.currentWordIndex.observe(viewLifecycleOwner) {
            if (it != words.size) {
                binding.tvWordValue.text =
                    getString(R.string.word_count, it.plus(1).toString(), words.size.toString())
            }
        }

        gameViewModel.isGameFinished.observe(viewLifecycleOwner) {
            MaterialAlertDialogBuilder(requireContext()).apply {
                setTitle(getString(R.string.game_complete))
                setMessage(getString(R.string.game_complete_message, finalScore.toString()))
                setCancelable(false)
                setPositiveButton(
                    getString(R.string.ok)
                ) { _, _ -> findNavController().popBackStack() }
                show()
            }
        }
    }

    /** This function will start the process to fetch the word from ViewModel **/
    private fun fetchWord() {
        gameViewModel.showWord()
    }

}