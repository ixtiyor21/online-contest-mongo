package uz.jl.mappers.user;

import org.bson.types.ObjectId;
import uz.jl.dto.user.UserCreateDto;
import uz.jl.dto.user.UserDto;
import uz.jl.dto.user.UserUpdateDto;
import uz.jl.entity.user.User;
import uz.jl.mappers.GenericCRUDMapper;
import uz.jl.mappers.GenericMapper;

public class UserMapper implements GenericCRUDMapper<UserCreateDto, UserUpdateDto, UserDto, User>, GenericMapper<User, UserDto> {
    @Override
    public UserCreateDto toCreate(User model) {
        return new UserCreateDto(model.getUsername(), model.getPassword(), model.getEmail());
    }

    @Override
    public UserUpdateDto toUpdate(User model) {
        return new UserUpdateDto(model.getId().toString(), model.getUsername(), model.getPassword(), model.getEmail());
    }

    @Override
    public UserDto to(User model) {
        return new UserDto(model.getId().toString(), model.getUsername(), model.getPassword(), model.getEmail(), model.getScore(), model.getQuizzes());
    }

    @Override
    public User fromCreate(UserCreateDto dto) {
        return new User(dto.getUsername(), dto.getPassword(), dto.getEmail());
    }

    @Override
    public User fromUpdate(UserUpdateDto dto) {
        User user = new User(dto.getUsername(), dto.getPassword(), dto.getEmail());
        user.setId(new ObjectId(dto.getId()));
        return user;
    }

    @Override
    public User from(UserDto dto) {
        User user = new User(dto.getUsername(), dto.getPassword(), dto.getEmail());
        user.setId(new ObjectId(dto.getId()));
        user.setScore(dto.getScore());
        user.setQuizzes(dto.getQuizzes());
        return user;
    }

    @Override
    public UserDto toList(User model) {
        return new UserDto(model.getId().toString(), model.getUsername(), model.getPassword(), model.getEmail(), model.getScore(), model.getQuizzes());
    }

    @Override
    public User fromList(UserDto dto) {
        User user = new User(dto.getUsername(), dto.getPassword(), dto.getEmail());
        user.setId(new ObjectId(dto.getId()));
        user.setScore(dto.getScore());
        user.setQuizzes(dto.getQuizzes());
        return user;
    }
}
