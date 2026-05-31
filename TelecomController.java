import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/telecom")
public class TelecomController {

    // Reutilizamos a sua classe de lógica de negócio original
    private final ReclamacaoServiceImpl logicaReclamacao = new ReclamacaoServiceImpl();

    // 1. Registrar Reclamação (POST)
    @PostMapping("/reclamacoes")
    public String registrarReclamacao(@RequestBody Map<String, String> payload) {
        String linha = payload.get("linha");
        String motivo = payload.get("motivo");
        return logicaReclamacao.registrarReclamacao(linha, motivo);
    }

    // 2. Consultar Status (GET)
    @GetMapping("/reclamacoes/{protocolo}")
    public String consultarStatus(@PathVariable String protocolo) {
        return logicaReclamacao.consultarStatusReclamacao(protocolo);
    }

    // 3. Consultar Empresa (GET)
    @GetMapping("/empresas/{cnpj}/linhas")
    public String consultarLinhasEmpresa(@PathVariable String cnpj) {
        return logicaReclamacao.consultarLinhasEmpresa(cnpj);
    }
}