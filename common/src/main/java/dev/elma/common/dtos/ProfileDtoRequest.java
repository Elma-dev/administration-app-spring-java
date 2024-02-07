package dev.elma.common.dtos;


import dev.elma.common.enums.RoleName;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProfileDtoRequest {
    private String profileName;
    private List<RoleName> rolesNames;
}
