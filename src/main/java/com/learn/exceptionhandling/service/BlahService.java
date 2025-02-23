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
        throw new BlahException("Blah, i am a teapot!");
    }

    public Object throwAnotherBlahException() {
        String errorMessage = ExceptionMessageBundle.getMessage(ExceptionMessageKeys.I_AM_ANOTHER_TEAPOT);
        throw new AnotherBlahException(errorMessage);
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
