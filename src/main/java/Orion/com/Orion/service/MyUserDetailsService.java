package Orion.com.Orion.service;

import Orion.com.Orion.model.StudentInfo;
import Orion.com.Orion.model.UserPrincipal;
import Orion.com.Orion.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private StudentRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<StudentInfo> student = repository.findByUsername(username);
        if(student == null){
            System.out.println("Student Not Found");
            throw new UsernameNotFoundException("No Student");
        }
        return new UserPrincipal(student.get());
    }
}
