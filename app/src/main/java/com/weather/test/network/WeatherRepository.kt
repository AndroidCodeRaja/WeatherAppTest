package com.weather.test.network


class WeatherRepository constructor(private val retrofitService: RetrofitService) {

    fun getWeather( city: String, appId: String) = retrofitService.getWeather(city, appId)
}