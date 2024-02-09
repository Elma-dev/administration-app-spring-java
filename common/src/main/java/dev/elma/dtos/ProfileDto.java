package dev.elma.dtos;



import dev.elma.entities.RoleEntity;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProfileDto {
    private Long id;
    private String profileName;
    private List<RoleEntity> roles;
}
