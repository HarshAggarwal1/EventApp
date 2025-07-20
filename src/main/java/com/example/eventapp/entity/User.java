package com.example.eventapp.entity;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, nullable=false)
    private String username;
    @Column(nullable=false)
    private String password;
    @ElementCollection(fetch=FetchType.EAGER)
    private Set<String> roles;
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String u) { username = u; }
    public String getPassword() { return password; }
    public void setPassword(String p) { password = p; }
    public Set<String> getRoles() { return roles; }
    public void setRoles(Set<String> r) { roles = r; }
}
