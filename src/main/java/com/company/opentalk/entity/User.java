package com.company.opentalk.entity;

import com.company.opentalk.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roleList = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Employee employee;

    public User(String email, String password, boolean enabled) {
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    public UserDto toDto() {
        List<String> roles = this.getRoleList().stream().map(Role::getRoleName).collect(Collectors.toList());
        return UserDto.builder()
                .userId(this.getUserId())
                .email(this.getEmail())
                .roles(roles)
                .enabled(this.isEnabled())
                .build();
    }

    public void addRole(List<Role> roles) {
        for (Role role : roles) {
            roleList.add(role);
        }
        for (Role role : roleList) {
            System.out.println(role.getRoleName());
        }
    }

}
