package com.springweek2.springweek2.contoller;

import com.springweek2.springweek2.model.UserRoleEnum;
import com.springweek2.springweek2.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails == null) {
        }
        else{
            model.addAttribute("username", userDetails.getUsername());
            if (userDetails.getUser().getRole() == UserRoleEnum.USER) {
                model.addAttribute("admin_role", true);
            }
        }
        return "index";
    }
}
