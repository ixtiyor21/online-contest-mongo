package uz.jl.dto.auth;
import lombok.*;
import uz.jl.dto.GenericBaseDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthCreateDto implements GenericBaseDto {
    private String username;
    private String password;
    private String email;
}
