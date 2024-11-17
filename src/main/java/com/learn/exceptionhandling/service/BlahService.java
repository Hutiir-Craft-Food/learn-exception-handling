package com.learn.exceptionhandling.service;

import com.learn.exceptionhandling.exception.AnnotatedBlahException;
import com.learn.exceptionhandling.exception.AnotherBlahException;
import com.learn.exceptionhandling.exception.BlahException;
import com.learn.exceptionhandling.exception.UnknownBlahException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class BlahService {

    public Object throwBlahException() {
        throw new BlahException("Blah, i am a teapot!");
    }

    public Object throwAnotherBlahException() {
        throw new AnotherBlahException("Blah, i am just another teapot!");
    }

    public Object throwUnknownBlahException() {
        throw new UnknownBlahException("Blah, i am an unknown teapot!");
    }

    public Object throwAnnotatedBlahException() {
        log.error("Blah, i am an annotated teapot!");
        throw new AnnotatedBlahException("Трясця, я - анотований чайник!");
    }
}
