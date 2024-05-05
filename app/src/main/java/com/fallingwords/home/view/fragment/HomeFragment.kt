package com.fallingwords.home.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fallingwords.R
import com.fallingwords.databinding.FragmentHomeBinding
import com.fallingwords.home.model.datamodel.Word
import com.fallingwords.home.viewmodel.WordsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

/** The fragment shows the UI of Home screen **/
@AndroidEntryPoint
class HomeFragment : Fragment() {

    /** This variable will store the instance of HomeFragment layout file views **/
    private lateinit var binding: FragmentHomeBinding

    /** This variable will store the instance of WordsViewModel **/
    private val wordsViewModel: WordsViewModel by viewModels()

    /** This variable will store the words fetched from the API or Database **/
    private var words: List<Word> = listOf()

    /** This function will create the view of the layout file defined **/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    /** This function will be called after the view is created and used to initialize the views and values **/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    /** This function will start calling the functions to initialize views **/
    private fun initViews() {
        setListeners()
        observeLiveData()
        fetchWords()
    }

    /** This function will set the listener for the views used **/
    private fun setListeners() {
        binding.ivPlay.setOnClickListener {
            if (words.isEmpty()) {
                MaterialAlertDialogBuilder(requireContext()).apply {
                    setTitle(getString(R.string.no_internet))
                    setMessage(getString(R.string.no_internet_detected_message))
                    setPositiveButton(
                        getString(R.string.ok)
                    ) { _, _ -> fetchWords() }
                    show()
                }
            } else {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToGameFragment(
                        words = words.toTypedArray()
                    )
                )
            }
        }
    }

    /** This function will observe the LiveData called in the ViewModel **/
    private fun observeLiveData() {
        wordsViewModel.words.observe(viewLifecycleOwner) {
            words = it
        }
    }

    /** This function will call the APIs to fetch the words **/
    private fun fetchWords() {
        wordsViewModel.fetchWords()
    }

}