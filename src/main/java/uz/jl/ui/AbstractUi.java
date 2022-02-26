package uz.jl.ui;

import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.utils.Color;
import uz.jl.utils.Print;

public abstract class AbstractUi<S> {
    protected final S service;

    protected AbstractUi(S service) {
        this.service = service;
    }

    protected <T> void showResponse(ResponseEntity<Data<T>> response) {
        Print.println(Color.CYAN, response.getData().getBody());
    }
}
