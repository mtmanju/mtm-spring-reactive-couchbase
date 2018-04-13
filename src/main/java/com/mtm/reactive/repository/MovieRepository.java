package com.mtm.reactive.repository;

import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;

import com.mtm.reactive.model.Movie;

public interface MovieRepository extends ReactiveCouchbaseRepository<Movie, String> {
}