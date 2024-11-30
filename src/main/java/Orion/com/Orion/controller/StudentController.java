package Orion.com.Orion.controller;

import Orion.com.Orion.model.StudentInfo;
import Orion.com.Orion.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orion")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/register")
    public StudentInfo register(@RequestBody StudentInfo student) throws Exception {
        return studentService.register(student);
    }

    @PostMapping("/login")
    public String login(@RequestBody StudentInfo student){
        return studentService.verify(student);
    }
}
