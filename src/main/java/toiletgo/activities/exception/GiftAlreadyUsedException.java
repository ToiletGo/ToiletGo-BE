package toiletgo.activities.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class GiftAlreadyUsedException extends RuntimeException {
  public GiftAlreadyUsedException(String message) {
    super(message);
  }
}
