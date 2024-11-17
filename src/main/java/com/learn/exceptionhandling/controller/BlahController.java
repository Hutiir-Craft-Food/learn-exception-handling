package com.learn.exceptionhandling.controller;

import com.learn.exceptionhandling.exception.BlahException;
import com.learn.exceptionhandling.service.BlahService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping(path = "/blah", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BlahController {

    private final BlahService blahService;

    @GetMapping("/response-status-exception")
    public Object throwResponseStatusException() {
        try {
            return blahService.throwBlahException();
        } catch (BlahException ex) {
            log.error("ResponseStatusException: Blah, i am a teapot!");
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "Трясця, я чайник!", ex);
        }
    }

    @GetMapping("/blah-exception")
    public Object throwBlahException() {
        return blahService.throwBlahException();
    }

    @GetMapping("/another-blah-exception")
    public Object throwAnotherBlahException() {
        return blahService.throwAnotherBlahException();
    }

    @GetMapping("/unknown-blah-exception")
    public Object throwUnknownBlahException() {
        return blahService.throwUnknownBlahException();
    }

    @GetMapping("/annotated-blah-exception")
    public Object throwAnnotatedBlahException() {
        return blahService.throwAnnotatedBlahException();
    }

    @PutMapping(value = "/seller/{sellerId}/address/{addressId}")
    public Object updateCategory(
            @PathVariable Long sellerId,
            @PathVariable Long addressId,
            @RequestBody Object address
    ) {
        return new Object();
    }
}
