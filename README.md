# Sistema de Gerenciamento de Usuários
## Introdução
Este projeto é um sistema simples de gerenciamento de usuários desenvolvido em
Java.
Ele permite que os usuários sejam cadastrados, listados e excluídos por meio de
um menu interativo no console.
O sistema foi criado para fins acadêmicos, servindo como base para estudar
conceitos de **Programação Orientada a Objetos (POO)**, como classes, métodos,
listas e encapsulamento.
---
## Para que Serve
O sistema tem como objetivo simular uma aplicação de gerenciamento, mostrando de
forma prática:
- Como organizar o código em **camadas (Model, View, Controller)**;
- Como criar **menus interativos** para interação via console;
- Como **armazenar e manipular dados** em memória.
---
## Funcionalidades do Sistema
### [1] Usuários
- **Listar Usuários:** Exibe todos os usuários cadastrados na memória.
- **Cadastrar Usuário:** Solicita nome, CPF, cargo, login e senha, garantindo
que o CPF seja único.
- **Excluir Usuário:** Remove um usuário informando o CPF.
- **Voltar:** Retorna ao menu principal.
---
### [2] Projetos
- **Listar Projetos:** Mostra todos os projetos cadastrados.
- **Cadastrar Projeto:** Cria um novo projeto com dados básicos.
- **Alterar Status:** Atualiza informações de um projeto existente.
- **Vincular Equipe:** Associa uma equipe ao projeto.
- **Voltar:** Retorna ao menu principal.
---
### [3] Equipes
- **Listar Equipes:** Exibe todas as equipes cadastradas.
- **Cadastrar Equipe:** Cria uma nova equipe.
- **Adicionar Usuário:** Associa um usuário existente a uma equipe.
- **Remover Usuário:** Remove um usuário de uma equipe.
- **Voltar:** Retorna ao menu principal.
---
### [4] Sair
- Encerra o sistema exibindo uma mensagem de finalização.
---
## Exemplo de Uso
```
MENU
[1]Usuarios
[2]Projetos
[3]Equipes
[4]Sair
Selecione uma opção: 1
Menu
[1]Exibir Usuários
[2]Cadastrar um novo Usuário
[3]Excluir um Usuário
[4]Voltar
Escolha uma opção: 2
Nome: João Silva
CPF: 123456789
Cargo: Desenvolvedor
Login: joao
Senha: 1234
Usuário João Silva cadastrado com sucesso!
```
---
## Regras de Negócio
- Cada usuário deve possuir um **CPF único** para evitar duplicidade.
- Os dados ficam armazenados apenas **em memória**; ao encerrar o programa, as
informações são perdidas.
- O sistema deve exibir mensagens informativas ao usuário para confirmar cada
ação.
- As operações são feitas somente pelo menu principal, garantindo **facilidade
de navegação**.
---
## Detalhes Técnicos
Entrada de dados: Feita através da classe Scanner, recebendo informações
diretamente do console.
Estrutura de Dados: Utilização de ArrayList para armazenar os usuários
dinamicamente.
---
---
## Como Executar
1. Certifique-se de ter o **Java** instalado (Java 8 ou superior).
2. Abra um terminal na pasta do projeto.
3. Compile os arquivos:
```bash
javac Main.java
```
4. Execute o programa:
```bash
java Main
```
Ou, se estiver usando uma IDE como **IntelliJ IDEA** ou **Eclipse**, basta abrir
o projeto e executar a classe `Main.java`.
