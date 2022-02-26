package uz.jl.ui.user;

import org.bson.types.ObjectId;
import uz.jl.container.UNIContainer;
import uz.jl.criteria.user.UserCriteria;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.dto.user.UserDto;
import uz.jl.dto.user.UserUpdateDto;
import uz.jl.entity.user.User;
import uz.jl.mappers.user.UserMapper;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.user.UserService;
import uz.jl.ui.AbstractUi;
import uz.jl.ui.GenericUi;
import uz.jl.utils.Color;
import uz.jl.utils.Input;
import uz.jl.utils.Print;

import java.util.List;

public class UserUi extends AbstractUi<UserService> implements GenericUi {
    private static final UserMapper MAPPER = UNIContainer.getBean(UserMapper.class);

    public UserUi(UserService service) {
        super(service);
    }

    @Override
    public void create() {
        String username = Input.getStr("username = ");
        String password = Input.getStr("password = ");
        String email = Input.getStr("email = ");
        UserCreateDto dto = MAPPER.toCreate(new User(username, password, email));
        ResponseEntity<Data<String>> response = service.create(dto);
        showResponse(response);
    }

    @Override
    public void update() {
        String id = Input.getStr("id = ");
        String username = Input.getStr("username = ");
        String password = Input.getStr("password = ");
        String email = Input.getStr("email = ");
        User user = new User(username, password, email);
        user.setId(new ObjectId(id));
        UserUpdateDto dto = MAPPER.toUpdate(user);
        service.update(dto);
        Print.println(Color.BLUE, "success");
    }

    @Override
    public void delete() {
        String id = Input.getStr("id = ");
        service.delete(id);
        Print.println(Color.BLUE, "success");
    }

    @Override
    public void get() {
        String id = Input.getStr("id = ");
        ResponseEntity<Data<UserDto>> response = service.get(id);
        showResponse(response);
    }

    @Override
    public void list() {
        ResponseEntity<Data<List<UserDto>>> response = service.list(new UserCriteria());
        showResponse(response);
    }
}
