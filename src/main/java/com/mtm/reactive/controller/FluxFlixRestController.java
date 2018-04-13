package com.mtm.reactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtm.reactive.model.Movie;
import com.mtm.reactive.service.FluxFlixService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
class FluxFlixRestController {

	private final FluxFlixService fluxFlixService;

	public FluxFlixRestController(FluxFlixService fluxFlixService) {
		this.fluxFlixService = fluxFlixService;
	}

	@GetMapping("/{id}")
	public Mono<Movie> byId(@PathVariable String id) {
		return fluxFlixService.byId(id);
	}

	@GetMapping(value = "/{id}/events", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Movie> streamStreams(@PathVariable String id) {
		return fluxFlixService.streamStreams(id);
	}

	@GetMapping
	public Flux<Movie> all() {
		return fluxFlixService.all();
	}

}