package uz.jl.services.auth;

import uz.jl.dto.auth.AuthCreateDto;
import uz.jl.repository.auth.AuthRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.AbstractService;

public class AuthService extends AbstractService<AuthRepository> {
    public AuthService(AuthRepository repository) {
        super(repository);
    }

    public ResponseEntity<Data<String>> login(String username, String password) {
        try {
            String result = repository.login(username, password);
            return new ResponseEntity<>(new Data<>(result));
        } catch (Exception e) {
            return new ResponseEntity<>(new Data<>(e.getMessage()));
        }
    }

    public ResponseEntity<Data<String>> register(AuthCreateDto dto) {
        String result = repository.register(dto);
        return new ResponseEntity<>(new Data<>(result));
    }

    public ResponseEntity<Data<String>> logout() {
        String result = repository.logout();
        return new ResponseEntity<>(new Data<>(result));
    }
}
