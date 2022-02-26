package uz.jl.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.jl.enums.HttpStatus;

/**
 * @param <D> response body type
 */
@Getter
@Setter
@ToString
public class ResponseEntity<D> {
    private D data;
    private Integer status;

    public ResponseEntity(D data) {
        this(data, HttpStatus.HTTP_200);
    }

    public ResponseEntity(D data, HttpStatus status) {
        this.data = data;
        this.status = status.getCode();
    }
}
