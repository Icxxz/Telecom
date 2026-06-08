package com.telecom.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Objeto Distribuído 2
@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final ReclamacaoServiceImpl servicoCentral;

    // Injeção da Referência
    public EmpresaController(ReclamacaoServiceImpl servicoCentral) {
        this.servicoCentral = servicoCentral;
    }

    // Método Remoto 3
    @GetMapping("/linhas")
    public String consultarLinhasEmpresa(@RequestParam String cnpj) {
        return servicoCentral.consultarLinhasEmpresa(cnpj);
    }
}