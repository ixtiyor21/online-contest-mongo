package uz.jl.services.user;

import uz.jl.criteria.user.UserCriteria;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.dto.user.UserDto;
import uz.jl.dto.user.UserUpdateDto;
import uz.jl.repository.user.UserRepository;
import uz.jl.response.Data;
import uz.jl.response.ResponseEntity;
import uz.jl.services.AbstractService;
import uz.jl.services.GenericCRUDService;
import uz.jl.services.GenericService;

import java.util.List;

public class UserService extends AbstractService<UserRepository> implements GenericCRUDService<UserCreateDto, UserUpdateDto, UserDto, String>, GenericService<UserDto, UserCriteria> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Data<String>> create(UserCreateDto dto) {
        String result = repository.create(dto);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<Void>> update(UserUpdateDto dto) {
        Void result = repository.update(dto);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<Void>> delete(String key) {
        Void result = repository.delete(key);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<UserDto>> get(String key) {
        UserDto result = repository.get(key);
        return new ResponseEntity<>(new Data<>(result));
    }

    @Override
    public ResponseEntity<Data<List<UserDto>>> list(UserCriteria criteria) {
        List<UserDto> result = repository.list(criteria);
        return new ResponseEntity<>(new Data<>(result));
    }
}
