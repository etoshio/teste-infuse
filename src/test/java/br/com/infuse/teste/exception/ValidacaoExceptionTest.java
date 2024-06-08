package br.com.infuse.teste.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class ValidacaoExceptionTest {
    @Mock
    Object backtrace;
    @Mock
    Throwable cause;
    //Field stackTrace of type StackTraceElement - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    List<Throwable> SUPPRESSED_SENTINEL;
    @Mock
    List<Throwable> suppressedExceptions;
    @InjectMocks
    ValidacaoException validacaoException;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMensagem(){
        new ValidacaoException("erro");
    }
}