package c4compile.jwtdemo.superadmin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("super-admin")
public class SuperAdminController {
    @GetMapping("something")
    public String get() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        throw new IllegalStateException(jwt.getClaimAsString("preferred_username") + " should not have access to this");
    }
}
