package Orion.com.Orion.service;

import Orion.com.Orion.model.*;
import Orion.com.Orion.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentService {
    private final StudentRepository infoRepository;
    private final StudentBenefitsRepository benefitsRepository;
    private final TasksRepository tasksRepository;
    private final NewProjetctsRepository newProjetctsRepository;
    private final AchievementsRepository achievementsRepository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JWTService jwtService;

    public StudentInfo register(StudentInfo student) throws Exception {
        Optional<StudentInfo> newStudent = infoRepository.findByUsername(student.getUsername());
        if(newStudent.isPresent()){
            throw new Exception("Username is taken!");
        }
        student.setPassword(encoder.encode(student.getPassword()));
        return infoRepository.save(student);
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

    public StudentBenefits getStudentBenefits(String userName) {
        Optional<StudentInfo> student = getStudentByUsername(userName);
        return benefitsRepository.findByStudentInfo_Id(student.get().getId())
                .orElseThrow(() -> new RuntimeException("Student benefits not found for user: " + userName));
    }

    public List<AssignedTasks> getAssignedTasks(Integer studentID) {
        List<AssignedTasks> student = tasksRepository.findByStudentInfo_Id(studentID);
        return student;
    }

    public Optional<StudentInfo> getStudentByUsername(String userName) {
        return Optional.ofNullable(infoRepository.findByUsername(userName))
                .orElseThrow(() -> new RuntimeException("Student not found with username: " + userName));
    }

    public List<Achievements> getAchievements(Integer benefitId) {
        return achievementsRepository.findAchievementsByStudentBenefits_BenefitId(benefitId);
    }

    public List<NewProjects> getNewProjects() {
        return newProjetctsRepository.findAll();
    }

    public StudentInfo getStudentInfo(Integer id) throws Exception {
        Optional<StudentInfo> studentInfo = infoRepository.findById(id);
        if(!studentInfo.isPresent()){
            throw new Exception("Student Not Found while getting info!");
        }
        return studentInfo.get();
    }
}