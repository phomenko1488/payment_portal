package iam.phomenko.payment_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response {
    private int code;
    private String text;

    public static ResponseEntity<Object> ok(Object object) {
        return new ResponseEntity<>(object, HttpStatus.OK);
    }
    public static ResponseEntity<Object> ok(Object object,int code) {
        return new ResponseEntity<>(object, HttpStatus.valueOf(code));
    }
    public static ResponseEntity<Object> error(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), message));
    }

    public static ResponseEntity<Object> validationError(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(HttpStatus.BAD_REQUEST.value(), message));
    }
}
