package dev.elma.dtos;

import dev.elma.enums.RoleName;
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
