package uz.jl.dto.user;

import lombok.*;
import uz.jl.dto.GenericDto;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto extends GenericDto {
    private String id;
    private String username;
    private String password;
    private String email;
}
