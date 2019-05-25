package personal.teslafoil.zubale.quote.controllers.advice;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class Advice {

    /**
     * The map of exceptions to error handling information.
     */
    private static final Map<Class<?>, Mapping> MAPPINGS = new HashMap<>();

    static {
        /*
         * If an exception is thrown and processed by controller advice, this is
         * the default mapping that is used if an instance of an unmapped
         * exception class was thrown. It is recommended that a mapping be added
         * if a different logging level and HTTP status is needed.
         */
        MAPPINGS.put(
                Exception.class,
                new Mapping(Level.ERROR, HttpStatus.INTERNAL_SERVER_ERROR)
                );
        MAPPINGS.put(
                HttpMessageConversionException.class,
                new Mapping(Level.ERROR, HttpStatus.BAD_REQUEST)
                );
    }

    /**
     * Processes an exception into an error response.
     *
     * @param exception
     *            The exception to process.
     *
     * @return The error response.
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        return getMapping(exception).process(exception);
    }

    private Mapping getMapping(Throwable exception) {
        for (Class<?> clazz = exception.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            if (MAPPINGS.containsKey(clazz)) {
                return MAPPINGS.get(clazz);
            }
        }
        // Use the default mapping.
        return MAPPINGS.get(Exception.class);
    }

    /**
     * Manages a recognized set of logging levels.
     */
    private enum Level {
        TRACE, DEBUG, INFO, WARN, ERROR
    }

    /**
     * Maps an exception to an error level and HTTP status.
     */
    private static class Mapping {
        /**
         * The logging level.
         */
        private Level level;

        /**
         * The HTTP status.
         */
        private HttpStatus status;

        /**
         * Initializes the new map.
         *
         * @param level
         *            The logging level.
         * @param status
         *            The HTTP status.
         */
        private Mapping(Level level, HttpStatus status) {
            this.level = level;
            this.status = status;
        }

        /**
         * Processes an exception by logging and creating a response.
         *
         * @param exception
         *            The exception.
         *
         * @return The response.
         */
        public ResponseEntity<ErrorResponse> process(Exception exception) {
            log(exception);
            return new ResponseEntity<>(convert(exception), status);
        }

        /**
         * Converts an exception into an error response.
         *
         * @param exception
         *            The exception.
         *
         * @return The new error response.
         */
        private ErrorResponse convert(Exception exception) {
            ErrorResponse error = new ErrorResponse();

            error.setErrorCode(status.value());
            error.setMessage(exception.getMessage());
            error.setStackTrace(ExceptionUtils.getStackTrace(exception));

            return error;
        }

        /**
         * Logs the exception using the appropriate level.
         *
         * @param exception
         *            The exception.
         */
        private void log(Exception exception) {
            switch (level) {
            case TRACE:
                log.trace(exception.getMessage(), exception);
                break;
            case DEBUG:
                log.debug(exception.getMessage(), exception);
                break;
            case INFO:
                log.info(exception.getMessage(), exception);
                break;
            case WARN:
                log.warn(exception.getMessage(), exception);
                break;
            case ERROR:
                log.error(exception.getMessage(), exception);
                break;
            default:
                break;
            }
        }
    }
}
