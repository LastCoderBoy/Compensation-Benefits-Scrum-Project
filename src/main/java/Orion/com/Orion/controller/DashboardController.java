//package Orion.com.Orion.controller;
//
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@AllArgsConstructor
//@RequestMapping("api/v1/student/dashboard")
//public class DashboardController {
//    @Autowired
//    private final DashboardService dashboardService;
//
//    @GetMapping("/main/{id}")
//    public String studentHomePage(@PathVariable String id){
//        dashboardService.home(id);
//        return "dashboard";
//    }
//}
