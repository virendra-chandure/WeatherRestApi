package com.hackerrank.weather.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.weather.model.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
	Optional<List<Weather>> findByDateAndCity(String date, String city);
	Optional<List<Weather>> findByCity(String city);
	Optional<List<Weather>> findByDate(String string);
	Optional<List<Weather>> findByDateAndCityOrderByDateDesc(String date, String city);
	Optional<List<Weather>> findByDateAndCityOrderByDateAsc(String date, String city);
	Optional<List<Weather>> findByDateOrderByDateDesc(String date);
	Optional<List<Weather>> findByDateOrderByDateAsc(String date);
}
