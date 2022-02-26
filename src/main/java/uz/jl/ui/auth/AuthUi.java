package uz.jl.ui.auth;

import uz.jl.container.UNIContainer;
import uz.jl.dto.auth.AuthCreateDto;
import uz.jl.entity.user.User;
import uz.jl.mappers.auth.AuthMapper;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.auth.AuthService;
import uz.jl.ui.AbstractUi;
import uz.jl.utils.Input;

public class AuthUi extends AbstractUi<AuthService> {
    private static final AuthMapper MAPPER = UNIContainer.getBean(AuthMapper.class);

    public AuthUi(AuthService service) {
        super(service);
    }

    public void login() {
        String username = Input.getStr("username = ");
        String password = Input.getStr("password = ");
        ResponseEntity<Data<String>> response = service.login(username, password);
        showResponse(response);
    }

    public void register() {
        String username = Input.getStr("username = ");
        String password = Input.getStr("password = ");
        String email = Input.getStr("email = ");
        User user = new User(username, password, email);
        AuthCreateDto dto = MAPPER.toCreate(user);
        ResponseEntity<Data<String>> response = service.register(dto);
        showResponse(response);
    }

    public void logout() {
        ResponseEntity<Data<String>> response = service.logout();
        showResponse(response);
    }
}
