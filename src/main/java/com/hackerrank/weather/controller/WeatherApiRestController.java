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

			if (sort.isPresent() && sort.get().equals("-date")) {
				return weatherRepository.findByDateAndCityIgnoreCaseOrderByDateDesc(date.get(), city.get());
			} else if (sort.isPresent() && sort.get().equals("date")) {
				weatherRepository.findByDateAndCityIgnoreCaseOrderByDateAsc(date.get(), city.get());
			} else {
				weatherRepository.findByDateAndCityIgnoreCase(date.get(), city.get());
			}
		} else if (date.isPresent()) {
			if (sort.isPresent() && sort.get().equals("-date")) {
				weatherRepository.findByDateOrderByDateDesc(date.get());
			} else if (sort.isPresent() && sort.get().equals("date")) {
				weatherRepository.findByDateOrderByDateAsc(date.get());
			} else {
				return weatherRepository.findByDate(city.get());
			}
		} else if (city.isPresent()) {
			return weatherRepository.findByCityIgnoreCase(city.get());
		}
		return Optional.ofNullable(weatherRepository.findAll());

	}

	@GetMapping("/{id}")
	public Weather getWeatherById(@PathVariable Integer id) {
		return weatherRepository.findById(id).orElseThrow(() -> new WhetherNotFoundException());
	}

}
