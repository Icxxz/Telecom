import requests
import sys

# Substitua 'localhost' pelo IP do computador do seu colega na hora do teste
BASE_URL = "http://localhost:8080/api/telecom"


def menu():
    while True:
        print("\n--- TELECOM ATENDIMENTO (CLIENTE PYTHON) ---")
        print("1. Registrar Nova Reclamação")
        print("2. Consultar Status de Protocolo")
        print("3. Consultar Linhas Corporativas")
        print("4. Sair")

        opcao = input("Escolha uma opção: ")

        if opcao == '1':
            linha = input("Número da Linha: ")
            motivo = input("Descrição do Problema: ")
            payload = {"linha": linha, "motivo": motivo}
            resposta = requests.post(f"{BASE_URL}/reclamacoes", json=payload)
            print(f"\n[SERVIDOR]: {resposta.text}")

        elif opcao == '2':
            protocolo = input("Digite o número do protocolo: ")
            resposta = requests.get(f"{BASE_URL}/reclamacoes/{protocolo}")
            print(f"\n[STATUS ATUAL]: {resposta.text}")

        elif opcao == '3':
            cnpj = input("Digite o CNPJ da Empresa: ")
            resposta = requests.get(f"{BASE_URL}/empresas/{cnpj}/linhas")
            print(f"\n[DADOS DA EMPRESA]: {resposta.text}")

        elif opcao == '4':
            print("Saindo do sistema...")
            sys.exit()
        else:
            print("Opção inválida.")


if __name__ == "__main__":
    menu()
