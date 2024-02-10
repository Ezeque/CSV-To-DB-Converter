package teste.pagamento.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import teste.pagamento.rest.service.BatchService;

import java.io.InputStream;

@RestController
@RequestMapping("/batch")
public class BatchController {
    @Autowired
    BatchService service;
    @GetMapping("/start")
    public ResponseEntity<String> startProcessing(@RequestBody MultipartFile file){
        try {
            service.startJob(file);
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("Processamento Iniciado", HttpStatus.OK);
    }
}
