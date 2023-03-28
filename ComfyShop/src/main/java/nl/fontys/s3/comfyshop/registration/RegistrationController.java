package nl.fontys.s3.comfyshop.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
@AllArgsConstructor
public class RegistrationController {
    private RegistrationService registrationService;
    public String register(@RequestBody RegistrationRequest request){
        return registrationService.register(request);
    }
}
