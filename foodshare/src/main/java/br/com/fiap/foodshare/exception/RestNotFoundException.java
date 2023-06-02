package br.com.fiap.foodshare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RestNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4804350595234559197L;

	public RestNotFoundException(String message) {
        super(message);
    }

}