package dev.elma.dtos;


import dev.elma.entities.ProfileEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private ProfileEntity profile;
}
