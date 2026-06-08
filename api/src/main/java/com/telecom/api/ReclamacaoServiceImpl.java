package com.telecom.api; 

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

// O @Service transforma essa classe em um objeto único na memória. 
// Isso simula a Passagem por Referência exigida para a execução dos objetos remotos.
@Service 
public class ReclamacaoServiceImpl {
    
    private Map<String, String> chamadosAbertos;
    private Map<String, ClienteEmpresa> empresasCadastradas; 

    public ReclamacaoServiceImpl() {
        this.chamadosAbertos = new HashMap<>();
        this.empresasCadastradas = new HashMap<>();
        
        // Populando dados para provar as agregações e extensões
        ClienteEmpresa empresaTeste = new ClienteEmpresa("11.222.333/0001-44", "Tech Corp S.A.");
        
        Linha linha1 = new Linha("85-7777", "Diretoria");
        linha1.adicionarServico(new SigaMe(29.90)); // Agregação + Extensão
        
        Linha linha2 = new Linha("85-8888", "Recepção");
        linha2.adicionarServico(new Secretaria(15.50, 100)); // Agregação + Extensão
        
        empresaTeste.adicionarLinha(linha1);
        empresaTeste.adicionarLinha(linha2);
        
        empresasCadastradas.put(empresaTeste.getCnpj(), empresaTeste);
    }

    public String registrarReclamacao(String numeroLinha, String motivo) {
        String protocolo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        chamadosAbertos.put(protocolo, "Linha: " + numeroLinha + " - Motivo: " + motivo + " | Status: EM ANÁLISE");
        return "Reclamação registrada com sucesso! Seu protocolo é: " + protocolo;
    }

    public String consultarStatusReclamacao(String protocolo) {
        return chamadosAbertos.getOrDefault(protocolo, "Protocolo não encontrado no sistema.");
    }

    public String consultarLinhasEmpresa(String cnpj) {
        ClienteEmpresa emp = empresasCadastradas.get(cnpj);
        if (emp != null) {
            String resultado = "Empresa: " + emp.getNomeFantasia() + " | Linhas: ";
            for (Linha l : emp.getLinhasCorporativas()) {
                resultado += l.getNumeroTelefone() + " (" + l.getTitular() + "); ";
            }
            return resultado;
        }
        return "Empresa não encontrada no sistema.";
    }

    // 4º Método de negócio para invocação remota
    public String consultarServicosLinha(String cnpj, String numeroLinha) {
        ClienteEmpresa emp = empresasCadastradas.get(cnpj);
        if (emp != null) {
            for (Linha l : emp.getLinhasCorporativas()) {
                if (l.getNumeroTelefone().equals(numeroLinha)) {
                    if (l.getServicosContratados().isEmpty()) return "Nenhum serviço contratado.";
                    
                    String res = "Serviços ativos: ";
                    for (Servico s : l.getServicosContratados()) {
                        res += s.getNome() + " (R$ " + s.getValorMensal() + "); ";
                    }
                    return res;
                }
            }
        }
        return "Empresa ou Linha não localizada.";
    }
}
