package com.mtm.reactive.service;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.mtm.reactive.model.Movie;
import com.mtm.reactive.repository.MovieRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class FluxFlixService {

	private final MovieRepository movieRepository;

	public FluxFlixService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public Flux<Movie> streamStreams(String id) {
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));

		Flux<Movie> events = Flux.fromStream(Stream.generate(() -> new Movie(id, "Manju", randomUser())));
		return Flux.zip(interval, events).map(Tuple2::getT2);
	}

	private String randomUser() {
		String[] users = "alex, phil, anne, serge".split(",");
		return users[new Random().nextInt(users.length)];
	}

	public Mono<Movie> byId(String id) {
		return this.movieRepository.findById(id);
	}

	public Flux<Movie> all() {
		return this.movieRepository.findAll();
	}
}