package com.telecom.api;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Objeto Distribuído 1
@RestController
@RequestMapping("/api/reclamacoes")
public class ReclamacaoController {

    private final ReclamacaoServiceImpl servicoCentral;

    // Injeção da Referência
    public ReclamacaoController(ReclamacaoServiceImpl servicoCentral) {
        this.servicoCentral = servicoCentral;
    }

    // Método Remoto 1 (Passagem por Valor via JSON)
    @PostMapping
    public String registrarReclamacao(@RequestBody Map<String, String> payload) {
        return servicoCentral.registrarReclamacao(payload.get("linha"), payload.get("motivo"));
    }

    // Método Remoto 2
    @GetMapping("/{protocolo}")
    public String consultarStatus(@PathVariable String protocolo) {
        return servicoCentral.consultarStatusReclamacao(protocolo);
    }
}