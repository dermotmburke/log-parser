package com.github.dermotmburke.logparser.repository;

import com.github.dermotmburke.logparser.model.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, String> {
}
