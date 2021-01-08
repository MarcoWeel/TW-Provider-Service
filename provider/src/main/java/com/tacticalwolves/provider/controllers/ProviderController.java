package com.tacticalwolves.provider.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tacticalwolves.provider.entity.Provider;

@RestController
@RequestMapping("/providers")
@CrossOrigin("*")
public class ProviderController {

    private List<Provider> providers = Stream.of(
            new Provider("1", "provider1"),
            new Provider("2", "provider2")
    )
            .collect(Collectors.toList());

    @GetMapping(value = "/{id}")
    public ResponseEntity<Provider> getDetails(@PathVariable("id") String id) {
        Optional<Provider> provider$ = providers.stream().filter(p -> id.equals(p.getId())).findFirst();
        if (!provider$.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Provider>(provider$.get(), HttpStatus.ACCEPTED);
    }

}
