package br.com.andersonmatte.sendemail.controller;

import br.com.andersonmatte.sendemail.dto.EntradaDto;
import br.com.andersonmatte.sendemail.task.EmailTask;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;

@RestController
@RequestMapping(path = "/send-email")
public class EmailController {

    @PostMapping("/send")
    public ResponseEntity sendEmail(
            @RequestBody EntradaDto entradaDto) {
        // Crie um timer
        Timer timer = new Timer();
        // Crie uma tarefa para enviar o email
        EmailTask task = new EmailTask(entradaDto);
        // Agende a tarefa para ser executada a cada 1 minuto
        timer.schedule(task, 0, 60 * 1000);
        return ResponseEntity.ok().body(task.retornoDto.getRetorno());
    }

}
