package com.weather.test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.weather.test.R
import com.weather.test.databinding.FragmentFirstBinding
import com.weather.test.network.RetrofitService
import com.weather.test.network.WeatherRepository
import com.weather.test.viewModel.WeatherViewModel
import com.weather.test.viewModel.WeatherViewModelFactory


class WeatherFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    lateinit var viewModel: WeatherViewModel
    private val retrofitService = RetrofitService.getInstance()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = requireActivity().run {
            ViewModelProvider(
                this,
                WeatherViewModelFactory(WeatherRepository(retrofitService))
            ).get(WeatherViewModel::class.java)
        }
        binding.buttonFirst.setOnClickListener {
            viewModel.getWeatherInformation(binding.editText.text.toString())
            viewModel.city.value = (binding.editText.text.toString())
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}