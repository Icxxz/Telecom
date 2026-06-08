const readline = require('readline');

const BASE_URL = "http://localhost:8080/api"; // Caminho base encurtado

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function exibirMenu() {
    console.log("\n--- TELECOM ATENDIMENTO (CLIENTE NODE.JS) ---");
    console.log("1. Registrar Nova Reclamação");
    console.log("2. Consultar Status de Protocolo");
    console.log("3. Consultar Linhas Corporativas");
    console.log("4. Consultar Serviços de uma Linha");
    console.log("5. Sair");

    rl.question("Escolha uma opção: ", manipularOpcao);
}

async function manipularOpcao(opcao) {
    if (opcao === '1') {
        rl.question("Número da Linha: ", (linha) => {
            rl.question("Descrição do Problema: ", async (motivo) => {
                const response = await fetch(`${BASE_URL}/reclamacoes`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify({ linha, motivo })
                });
                const texto = await response.text();
                console.log(`\n[SERVIDOR]: ${texto}`);
                exibirMenu();
            });
        });
    } else if (opcao === '2') {
        rl.question("Digite o número do protocolo: ", async (protocolo) => {
            const response = await fetch(`${BASE_URL}/reclamacoes/${protocolo}`);
            const texto = await response.text();
            console.log(`\n[STATUS ATUAL]: ${texto}`);
            exibirMenu();
        });
    } else if (opcao === '3') {
        rl.question("Digite o CNPJ da Empresa: ", async (cnpj) => {
            const response = await fetch(`${BASE_URL}/empresas/linhas?cnpj=${encodeURIComponent(cnpj)}`);
            const texto = await response.text();
            console.log(`\n[DADOS DA EMPRESA]: ${texto}`);
            exibirMenu();
        });
    } else if (opcao === '4') {
        rl.question("Digite o CNPJ da Empresa: ", (cnpj) => {
            rl.question("Digite o número da linha: ", async (linha) => {
                const response = await fetch(`${BASE_URL}/servicos/linha?cnpj=${encodeURIComponent(cnpj)}&numeroLinha=${encodeURIComponent(linha)}`);
                const texto = await response.text();
                console.log(`\n[SERVIÇOS ATIVOS]: ${texto}`);
                exibirMenu();
            });
        });
    } else if (opcao === '5') {
        console.log("Saindo do sistema...");
        rl.close();
    } else {
        console.log("Opção inválida.");
        exibirMenu();
    }
}

exibirMenu();
