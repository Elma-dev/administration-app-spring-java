package dev.elma.common.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDtoRequest {
    private String username;
    private String email;
    private String password;
    private String profileName;
}
