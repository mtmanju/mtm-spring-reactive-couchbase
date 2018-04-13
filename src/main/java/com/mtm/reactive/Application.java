package com.mtm.reactive;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;

import com.mtm.reactive.model.Movie;
import com.mtm.reactive.repository.MovieRepository;

@SpringBootApplication
@EnableReactiveCouchbaseRepositories(considerNestedRepositories = true)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(MovieRepository movieRepository) {
//		return args -> {
//			movieRepository.deleteAll().subscribe(null, null, () -> {
//				Stream.of("Aeon Flux", "Enter the Mono<Void>", "THe Fluxinator", "Reactive Mongose on Plane",
//						"Y tu Mono Tambien", "Attack of Flux", "Back to the Future")
//						.map(name -> new Movie(UUID.randomUUID().toString(), name, randomGenre()))
//						.forEach(m -> movieRepository.save(m).subscribe(System.out::println));
//			});
//		};
		
		return args -> {
			Stream.of("Aeon Flux", "Enter the Mono<Void>", "The fluxinator", "Silence Of Lambdas",
					"Reactive Mongos on Plane", "Y Tu Mono Tambien", "Attack of fluxxes", "Back to the future")
					.map(name -> new Movie(UUID.randomUUID().toString(), name, randomGenre()))
					.forEach(movie -> movieRepository.save(movie).subscribe(System.out::println));
		};

	}

	private String randomGenre() {
		String[] genres = "horror, romcom, drama, action, documentary".split(",");
		return genres[new Random().nextInt(genres.length)];
	}

}
