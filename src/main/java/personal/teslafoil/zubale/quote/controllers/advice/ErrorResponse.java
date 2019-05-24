package personal.teslafoil.zubale.quote.controllers.advice;

import lombok.Data;

@Data
public class ErrorResponse {

    private int errorCode;
    private String message;
    private String stackTrace;

}
