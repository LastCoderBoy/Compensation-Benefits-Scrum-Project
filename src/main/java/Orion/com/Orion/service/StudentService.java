package Orion.com.Orion.service;

import Orion.com.Orion.model.StudentInfo;
import Orion.com.Orion.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    public StudentInfo register(StudentInfo student){
        student.setPassword(encoder.encode(student.getPassword()));
        return repository.save(student);
    }

    public String verify(StudentInfo student) {
        Authentication authentication =
                authManager
                        .authenticate(new UsernamePasswordAuthenticationToken(student.getUsername(), student.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(student.getUsername());
        }
        return "fail";
    }
}
