package com.hackerrank.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.weather.model.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
	List<Weather> findByDateAndCityIgnoreCase(String date, String city);

	List<Weather> findByCityIgnoreCase(String city);

	List<Weather> findByDate(String date);

	List<Weather> findByDateAndCityIgnoreCaseOrderByDateDesc(String date, String city);

	List<Weather> findByDateAndCityIgnoreCaseOrderByDateAsc(String date, String city);

	List<Weather> findByDateOrderByDateDesc(String date);

	List<Weather> findByDateOrderByDateAsc(String date);

	List<Weather> findAllByOrderByDateDesc();

	List<Weather> findAllByOrderByDateAsc();
}
