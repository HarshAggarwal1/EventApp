package com.example.eventapp.service;
import com.example.eventapp.dto.AuthRequest;
import com.example.eventapp.dto.AuthResponse;
import com.example.eventapp.entity.User;
import com.example.eventapp.repository.UserRepository;
import com.example.eventapp.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class AuthService {
    private final UserRepository repo;
    private final PasswordEncoder enc;
    private final AuthenticationManager authMgr;
    private final JwtUtil jwtUtil;
    public AuthService(UserRepository repo, PasswordEncoder enc, AuthenticationManager authMgr, JwtUtil jwtUtil) {
        this.repo = repo; this.enc = enc; this.authMgr = authMgr; this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(AuthRequest req) {
        User u = new User();
        u.setUsername(req.username());
        u.setPassword(enc.encode(req.password()));
        u.setRoles(Set.of("ROLE_USER"));
        repo.save(u);
        return new AuthResponse(jwtUtil.generateToken(u.getUsername(), u.getRoles()));
    }

    public AuthResponse login(AuthRequest req) {
        authMgr.authenticate(new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        User u = repo.findByUsername(req.username()).orElseThrow();
        return new AuthResponse(jwtUtil.generateToken(u.getUsername(), u.getRoles()));
    }
}
