package com.example.employee.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(nullable = false,unique = true)
    private String userName;

    private String password;
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable( name = "user_roles",joinColumns={@JoinColumn(name="USER_ID")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID")})
    Set<Role> roles = new HashSet<>();
}
