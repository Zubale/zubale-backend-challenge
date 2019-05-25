package personal.teslafoil.zubale.quote.controllers.advice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;

public class AdviceTest {

    private Advice advice;

    @Before
    public void setUp() {
        advice = new Advice();
    }

    @Test
    public void handleExceptionMapped() {
        String message = "handleExceptionMapped";
        HttpMessageConversionException exception = new HttpMessageConversionException(message);

        ResponseEntity<ErrorResponse> response = advice.handleException(exception);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getErrorCode());
        assertEquals(exception.getMessage(), response.getBody().getMessage());
    }

    @Test
    public void handleExceptionDefault() {
        String message = "handleExceptionDefault";
        RuntimeException exception = new RuntimeException(message);

        ResponseEntity<ErrorResponse> response = advice.handleException(exception);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getBody().getErrorCode());
        assertEquals(exception.getMessage(), response.getBody().getMessage());
    }
}
