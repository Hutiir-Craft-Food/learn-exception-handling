package com.learn.exceptionhandling.service;

import com.learn.exceptionhandling.exception.AnnotatedBlahException;
import com.learn.exceptionhandling.exception.AnotherBlahException;
import com.learn.exceptionhandling.exception.BlahException;
import com.learn.exceptionhandling.exception.UnknownBlahException;
import com.learn.exceptionhandling.i18n.ExceptionMessageBundle;
import com.learn.exceptionhandling.i18n.ExceptionMessageKeys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Slf4j
@Service
public class BlahService {

    public Object throwBlahException() {
        String errorMessage = ExceptionMessageBundle.getMessage(ExceptionMessageKeys.I_AM_TEAPOT);
        throw new BlahException(errorMessage);
    }

    public Object throwAnotherBlahException() {
        throw new AnotherBlahException("Blah, i am just another teapot!");
    }

    public Object throwUnknownBlahException() {
        throw new UnknownBlahException("Blah, i am an unknown teapot!");
    }

    public Object throwAnnotatedBlahException() {
        log.error("Blah, i am an annotated teapot!");
        String localizedErrorMessage = ExceptionMessageBundle
                .getLocalizedMessage(ExceptionMessageKeys.I_AM_ANNOTATED_TEAPOT, new Locale("UA"));
        throw new AnnotatedBlahException(localizedErrorMessage);
    }
}
