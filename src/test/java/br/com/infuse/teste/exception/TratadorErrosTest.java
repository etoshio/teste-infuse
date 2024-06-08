package br.com.infuse.teste.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.format.DateTimeParseException;

import static org.mockito.Mockito.mock;

class TratadorErrosTest {
    TratadorErros tratadorErros = new TratadorErros();

    @Test
    void testTratarErro404() {
        ResponseEntity<?> result = tratadorErros.tratarErro404();
        Assertions.assertNotNull(result);
    }

    @Test
    void testTratarErro400() {
        ResponseEntity<?> result = tratadorErros.tratarErro400(mock(MethodArgumentNotValidException.class));
        Assertions.assertNotNull(result);
    }

    @Test
    void testTratarValidacao() {
        ResponseEntity<?> result = tratadorErros.tratarValidacao(new ValidacaoException("mensagem"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testTratarValidacao2() {
        ResponseEntity<?> result = tratadorErros.tratarValidacao(new NotFoundException("mensagem"));
        Assertions.assertNotNull(result);
    }

    @Test
    void testTratarValidacao3() {
        ResponseEntity<?> result = tratadorErros.tratarValidacao(new DateTimeParseException("mensagem","a", 0));
        Assertions.assertNotNull(result);
    }
}