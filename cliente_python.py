import requests
import sys

BASE_URL = "http://localhost:8080/api" # Caminho base encurtado

def menu():
    while True:
        print("\n--- TELECOM ATENDIMENTO (CLIENTE PYTHON) ---")
        print("1. Registrar Nova Reclamação")
        print("2. Consultar Status de Protocolo")
        print("3. Consultar Linhas Corporativas")
        print("4. Consultar Serviços de uma Linha")
        print("5. Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == '1':
            linha = input("Número da Linha: ")
            motivo = input("Descrição do Problema: ")
            resposta = requests.post(f"{BASE_URL}/reclamacoes", json={"linha": linha, "motivo": motivo})
            print(f"\n[SERVIDOR]: {resposta.text}")

        elif opcao == '2':
            protocolo = input("Digite o número do protocolo: ")
            resposta = requests.get(f"{BASE_URL}/reclamacoes/{protocolo}")
            print(f"\n[STATUS ATUAL]: {resposta.text}")

        elif opcao == '3':
            cnpj = input("Digite o CNPJ da Empresa (Ex: 11.222.333/0001-44): ")
            resposta = requests.get(f"{BASE_URL}/empresas/linhas", params={"cnpj": cnpj})
            print(f"\n[DADOS DA EMPRESA]: {resposta.text}")
            
        elif opcao == '4':
            cnpj = input("Digite o CNPJ da Empresa: ")
            linha = input("Digite o número da linha (Ex: 85-7777): ")
            resposta = requests.get(f"{BASE_URL}/servicos/linha", params={"cnpj": cnpj, "numeroLinha": linha})
            print(f"\n[SERVIÇOS ATIVOS]: {resposta.text}")

        elif opcao == '5':
            print("Saindo do sistema...")
            sys.exit()
        else:
            print("Opção inválida.")

if __name__ == "__main__":
    menu()
