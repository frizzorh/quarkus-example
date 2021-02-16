package com.example;

import com.example.model.User;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class AppInitializer {

    @Inject
    private UserRepository userRepository;

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        userRepository.persist(new User(null, "frossi", "admin"));
    }
    void onStop(@Observes ShutdownEvent ev) {
    }
}