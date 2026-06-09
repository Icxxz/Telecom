# Sistema de Telecomunicações Distribuído (API REST com Spring Boot)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)
![Node.js](https://img.shields.io/badge/Node.js-43853D?style=for-the-badge&logo=node.js&logoColor=white)
![REST API](https://img.shields.io/badge/Architecture-REST_API-orange?style=for-the-badge)

Este repositório contém a implementação da entrega 3 da disciplina de **Sistemas Distribuídos** do curso de Engenharia de Computação da **Universidade Federal do Ceará (UFC) - Campus Quixadá**, sob orientação do **Professor Rafael Braga**.

O objetivo deste projeto é evoluir o sistema de atendimento de telecomunicações, abandonando o middleware RMI e reimplementando o serviço remoto através de uma arquitetura baseada em **Web Services / API REST**, com comunicação HTTP estruturada no modelo Cliente-Servidor.

**Regras do Projeto:** 
1. É terminantemente proibido o uso de Sockets nativos ou RMI. 
2. Os clientes devem ser implementados em, pelo menos, **2 (duas) linguagens de programação diferentes** da linguagem do servidor.
3. A avaliação exige a interação em rede real entre os computadores dos integrantes da dupla.

---

##  Arquitetura e Tecnologias

A aplicação foi migrada de um modelo de "Invocação Remota" (RPC/RMI) para o padrão arquitetural **REST**, garantindo maior interoperabilidade.

### 1. O Servidor (Java + Spring Boot)
A lógica de negócio (`ReclamacaoServiceImpl`) e as entidades de domínio (`Linha`, `Servico`, `ClienteEmpresa`) foram integralmente preservadas. A camada de rede manual (antigo `GatewayRMI`) foi substituída por um **Controller Spring Boot** (`TelecomController`), que expõe endpoints HTTP e gerencia a conversão de/para JSON de forma nativa.

### 2. Os Clientes (Poliglotismo)
Para atender à exigência de linguagens distintas:
* **Cliente 1 (Python):** Utiliza a biblioteca `requests` para interagir com a API de forma síncrona através de um menu interativo no terminal.
* **Cliente 2 (JavaScript / Node.js):** Utiliza a API `fetch` nativa e o módulo `readline` para prover uma interface de terminal assíncrona orientada a eventos.

---

## Fluxo de Execução Simplificado

1. **Interação CLI:** O usuário interage com o menu no cliente (Python ou Node.js) e insere os dados da reclamação ou da consulta.
2. **Requisição HTTP:** O cliente serializa os dados (quando necessário) e dispara uma requisição HTTP (`GET` ou `POST`) para o endpoint específico no servidor Spring Boot (ex: `/api/telecom/reclamacoes`).
3. **Roteamento e Desserialização:** O Tomcat embutido no Spring Boot recebe a requisição, o framework desserializa o JSON do *body* automaticamente em objetos Java e injeta no método correspondente do `TelecomController`.
4. **Processamento:** A lógica de persistência local em memória é executada (geração de protocolo UUID, buscas em HashMap).
5. **Retorno (Response):** O servidor devolve uma resposta HTTP 200 (OK) contendo o status ou protocolo gerado, que é exibido no console do cliente.

---

##  Como Compilar, Executar e Testar (Interação da Dupla)

A arquitetura foi projetada para rodar de forma distribuída. Um estudante deve rodar o servidor, enquanto o outro conecta através dos clientes. Ambos devem estar na mesma rede (LAN/VPN).

### Passo 1: O Servidor (Usuário A)

1. Descubra o IP local da sua máquina (use `ipconfig` no Windows ou `ip a` no Linux). Guarde este IP para passar ao Usuário B.
2. Abra o terminal na pasta raiz do projeto Java (onde está o arquivo `pom.xml`).
3. Execute o servidor Spring Boot com o Maven Wrapper:
```bash
./mvnw clean spring-boot:run
```

(No Windows, utilize mvnw clean spring-boot:run).
Aguarde a mensagem: Tomcat started on port 8080.

### Passo 2: Os Clientes (Usuário B)
Abra os arquivos fonte dos clientes (cliente.py e cliente.js).

Altere a variável BASE_URL para apontar para o IP do Estudante A:

Exemplo: BASE_URL = "http://192.168.1.50:8080/api/telecom"

Para executar o Cliente Python:
(Requer pip install requests)
```bash
python3 cliente.py
```

Para executar o Cliente Node.js:
(Requer Node.js instalado)

```bash
node cliente.js
```

https://drive.google.com/file/d/1FWH27zuOUlCZmy3JDZTqELAVQ5WJkqjE/view?usp=sharing
Video da prática
