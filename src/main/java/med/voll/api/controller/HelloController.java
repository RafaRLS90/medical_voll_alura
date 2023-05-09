package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//Anotação usada para Api Rest
@RequestMapping("/hello")//qual url que esse controller ira responder
public class HelloController {

    @GetMapping//método do tipo get chamado
    public String olaMundo () {
    return "Hello Word Spring!!!";
    }
}
