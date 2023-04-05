package com.weather.test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.weather.test.R
import com.weather.test.databinding.FragmentSecondBinding
import com.weather.test.model.WeatherInfo
import com.weather.test.model.WeatherResponse
import com.weather.test.network.RetrofitService
import com.weather.test.network.WeatherRepository
import com.weather.test.viewModel.WeatherViewModel
import com.weather.test.viewModel.WeatherViewModelFactory


class WeatherListFragment : Fragment(), WeatherAdapter.OnWeatherDetailsClickListener {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: WeatherViewModel
    private val retrofitService = RetrofitService.getInstance()
    private var adapter = WeatherAdapter(emptyList(), this)
    var arrayList: ArrayList<WeatherInfo>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
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
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        setObserver()
    }

    /**
     * Getting the list from viewmodel and observing it and adding it to adapter.
     */
    private fun setObserver() {
        viewModel.weatherList.observe(viewLifecycleOwner, Observer<WeatherResponse> {
            adapter = WeatherAdapter(it.list, this)
            binding.recyclerView.adapter = adapter
        })
        viewModel.city.observe(viewLifecycleOwner, Observer<String> {
            (activity as MainActivity?)?.updateTitle(it)
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * On click of each record we need to navigate to weather details fragment
     */
    override fun onWeatherDetailsClick(weatherInfo: WeatherInfo) {
        viewModel.list.value = weatherInfo
        findNavController().navigate(R.id.action_SecondFragment_to_WeatherDetailsFragment)
    }
}