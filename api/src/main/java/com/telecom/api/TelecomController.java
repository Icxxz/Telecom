package com.telecom.api;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/telecom")
public class TelecomController {

    // Instanciamos a sua classe original que já tem os métodos locais prontos
    private final ReclamacaoServiceImpl logicaReclamacao = new ReclamacaoServiceImpl();

    // 1. REGISTRAR RECLAMAÇÃO (Substitui a opção 1 do RMI)
    @PostMapping("/reclamacoes")
    public String registrarReclamacao(@RequestBody Map<String, String> payload) {
        // O Spring já leu o JSON do cliente e colocou dentro do mapa 'payload'
        String linha = payload.get("linha");
        String motivo = payload.get("motivo");
        
        // Chama seu método puro que gera o protocolo UUID
        return logicaReclamacao.registrarReclamacao(linha, motivo);
    }

    // 2. CONSULTAR STATUS (Substitui a opção 2 do RMI)
    // O cliente vai acessar por exemplo: /api/telecom/reclamacoes/ABC12345
    @GetMapping("/reclamacoes/{protocolo}")
    public String consultarStatus(@PathVariable String protocolo) {
        // O @PathVariable captura o texto direto da URL e joga na variável
        return logicaReclamacao.consultarStatusReclamacao(protocolo);
    }

    // 3. CONSULTAR EMPRESA (Substitui a opção 3 do RMI)
    @GetMapping("/empresas/{cnpj}/linhas")
    public String consultarLinhasEmpresa(@PathVariable String cnpj) {
        return logicaReclamacao.consultarLinhasEmpresa(cnpj);
    }
}