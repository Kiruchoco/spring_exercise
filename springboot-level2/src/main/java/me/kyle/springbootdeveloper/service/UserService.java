package me.kyle.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.kyle.springbootdeveloper.domain.User;
import me.kyle.springbootdeveloper.dto.AddUserRequest;
import me.kyle.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))  // 패스워드 암호화
                .build()).getId();
    }

    // 메서드 추가
    public User findById(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("Unexpected user"));
    }
}
