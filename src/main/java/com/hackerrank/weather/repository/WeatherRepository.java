package com.hackerrank.weather.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackerrank.weather.model.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
	List<Weather> findByDateAndCityIgnoreCase(Date date, String city);

	List<Weather> findByCityIgnoreCase(String city);

	List<Weather> findByDate(Date date);

	List<Weather> findByDateAndCityIgnoreCaseOrderByDateDesc(Date date, String city);

	List<Weather> findByDateAndCityIgnoreCaseOrderByDateAsc(Date date, String city);

	List<Weather> findByDateOrderByDateDesc(Date date);

	List<Weather> findByDateOrderByDateAsc(Date date);

	List<Weather> findAllByOrderByDateDesc();

	List<Weather> findAllByOrderByDateAsc();
}
