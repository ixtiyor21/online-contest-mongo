package uz.jl.response;

import lombok.Getter;
import lombok.ToString;

/**
 * @param <B> Body
 */
@Getter
@ToString
public class Data<B>{
    private final B body;
    private final Integer total;

    public Data(B body) {
        this(body, 0);
    }

    public Data(B body, Integer total) {
        this.body = body;
        this.total = total;
    }
}
