package com.learn.exceptionhandling.controller;

import com.learn.exceptionhandling.exception.BlahException;
import com.learn.exceptionhandling.i18n.ExceptionMessageBundle;
import com.learn.exceptionhandling.i18n.ExceptionMessageKeys;
import com.learn.exceptionhandling.service.BlahService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
            log.error("response-status-exception: {}", ex.getMessage());
            String localizedErrorMessage = ExceptionMessageBundle.getLocalizedMessage(ExceptionMessageKeys.I_AM_TEAPOT, new Locale("UA"));
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, localizedErrorMessage, ex);
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

    // ----------------------------------------
    //  some end-points that return json data:
    // ----------------------------------------

    @GetMapping(path = "/test")
    public Object getTest() {
        return List.of("blah", "test");
    }

    @PostMapping(path = "/test/{testId}")
    public Object postTest (
            @PathVariable Long testId,
            @RequestBody Object data
    ) {
        Map<String, Object> response = new HashMap<>();
        response.put("testId", testId);
        response.put("data", data);

        return response;
    }
}
