package com.company.opentalk.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer userId;

    private String email;

    private boolean enabled;

    private List<String> roles;

}
