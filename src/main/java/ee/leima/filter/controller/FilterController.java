package ee.leima.filter.controller;

import ee.leima.filter.dto.FilterRequest;
import ee.leima.filter.dto.FilterResponse;
import ee.leima.filter.service.FilterService;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/filter")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;

    @PutMapping("/upsert")
    public ResponseEntity<Void> upsertFilter(@RequestBody FilterRequest filterRequest) {
        try {
            filterService.saveOrUpdateFilter(filterRequest);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<List<FilterResponse>> getAll() {
        List<FilterResponse> filterResponses = filterService.getAll();
        if (filterResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(filterResponses, HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/filter/{id}")
    public ResponseEntity<Void> deleteFilterById(@PathVariable Long id) {
        try {
            filterService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
