package uz.jl.mappers.auth;

import uz.jl.dto.auth.AuthCreateDto;
import uz.jl.dto.auth.AuthDto;
import uz.jl.entity.user.User;
import uz.jl.enums.UserRole;

public class AuthMapper {
    public AuthCreateDto toCreate(User model) {
        return new AuthCreateDto(model.getUsername(), model.getPassword(), model.getEmail());
    }

    public User fromCreate(AuthCreateDto dto) {
        return new User(dto.getUsername(), dto.getPassword(), dto.getEmail(), 0, UserRole.STUDENT);
    }

    public AuthDto to(User model) {
        return null;
    }

    public User from(AuthDto dto) {
        return null;
    }
}
