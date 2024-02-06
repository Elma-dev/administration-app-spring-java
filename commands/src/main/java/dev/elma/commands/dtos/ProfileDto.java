package dev.elma.commands.dtos;


import dev.elma.commands.entities.RoleEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProfileDto {

    private String profileName;
    private List<RoleEntity> roles;
}
