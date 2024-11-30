package Orion.com.Orion.controller;

import Orion.com.Orion.model.*;
import Orion.com.Orion.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/student")
public class DashboardController {
    @Autowired
    private final StudentService studentService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("student", new StudentInfo());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute StudentInfo student, Model model) throws Exception {
        StudentInfo registeredStudent = studentService.register(student);
        model.addAttribute("student", registeredStudent);
        return "redirect:/api/v1/student/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Display login form
    }

    @PostMapping("/login")
    public String login(@ModelAttribute StudentInfo student, Model model) throws Exception {
        String jwt = studentService.verify(student);
        if (!"fail".equals(jwt)) {
            Integer studentId = studentService.getStudentByUsername(student.getUsername()).get().getId();
            return "redirect:/api/v1/student/dashboard/" + studentId;
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/dashboard/{id}")
    public String dashboardPage(Model model, @PathVariable Integer id) {

        try {
            StudentInfo student = studentService.getStudentInfo(id);

            StudentBenefits studentBenefits = studentService.getStudentBenefits(student.getUsername());
            System.out.println("Student Benefits: " + studentBenefits);
            List<AssignedTasks> assignedTasks = studentService.getAssignedTasks(student.getId());
            List<Achievements> achievements = studentService.getAchievements(studentBenefits.getBenefitId());
            List<NewProjects> newProjects = studentService.getNewProjects();

            // Add attributes to the model
            model.addAttribute("studentBenefits", studentBenefits);
            model.addAttribute("achievements", achievements);
            model.addAttribute("assignedTasks", assignedTasks);
            model.addAttribute("studentName", student.getName());
            model.addAttribute("newProjects", newProjects);

            return "dashboard";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
