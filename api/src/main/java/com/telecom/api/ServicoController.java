package com.telecom.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Objeto Distribuído 3
@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ReclamacaoServiceImpl servicoCentral;

    // Injeção da Referência
    public ServicoController(ReclamacaoServiceImpl servicoCentral) {
        this.servicoCentral = servicoCentral;
    }

    // Método Remoto 4
    @GetMapping("/linha")
    public String consultarServicosDaLinha(@RequestParam String cnpj, @RequestParam String numeroLinha) {
        return servicoCentral.consultarServicosLinha(cnpj, numeroLinha);
    }
}