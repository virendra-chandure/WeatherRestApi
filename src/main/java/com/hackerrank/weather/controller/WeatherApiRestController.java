package com.hackerrank.weather.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.weather.exception.WhetherNotFoundException;
import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;

@RestController
@RequestMapping("/weather")
public class WeatherApiRestController {

	@Autowired
	WeatherRepository weatherRepository;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Weather saveWeather(@RequestBody Weather weather) {
		return weatherRepository.saveAndFlush(weather);
	}

	@GetMapping
	public Optional<List<Weather>> getWeather(@RequestParam("date") Optional<String> date,
			@RequestParam("city") Optional<String> city, @RequestParam("sort") Optional<String> sort) {
		if (date.isPresent() && city.isPresent()) {
			
			if(sort.isPresent() && sort.get().equals("-date")) {
				return Optional.ofNullable(weatherRepository.findByDateAndCityOrderByDateDesc(date.get(), city.get())
						.orElseThrow(() -> new WhetherNotFoundException()));
			} else if(sort.isPresent() && sort.get().equals("date")){
				return Optional.ofNullable(weatherRepository.findByDateAndCityOrderByDateAsc(date.get(), city.get())
						.orElseThrow(() -> new WhetherNotFoundException()));
			} else {
			return Optional.ofNullable(weatherRepository.findByDateAndCity(date.get(), city.get())
					.orElseThrow(() -> new WhetherNotFoundException()));
			}
		} else if (date.isPresent()) {
			if(sort.isPresent() && sort.get().equals("-date")) {
				return Optional.ofNullable(weatherRepository.findByDateOrderByDateDesc(date.get())
						.orElseThrow(() -> new WhetherNotFoundException()));
			} else if(sort.isPresent() && sort.get().equals("date")){
				return Optional.ofNullable(weatherRepository.findByDateOrderByDateAsc(date.get())
						.orElseThrow(() -> new WhetherNotFoundException()));
			} else {
			return Optional.ofNullable(
					weatherRepository.findByDate(city.get()).orElseThrow(() -> new WhetherNotFoundException()));
			}
		} else if (city.isPresent()) {
			return Optional.ofNullable(
					weatherRepository.findByCity(city.get()).orElseThrow(() -> new WhetherNotFoundException()));
		} else {
			return Optional.ofNullable(weatherRepository.findAll());
		}
	}

	@GetMapping("/{id}")
	public Weather getWeatherById(@PathVariable Integer id) {
		return weatherRepository.findById(id).orElseThrow(() -> new WhetherNotFoundException());
	}

}
