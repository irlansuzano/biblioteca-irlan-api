# Biblioteca Irlan API

## Apresentação do Projeto
Esta API foi desenvolvida para gerenciar o fluxo de aluguel de livros de uma biblioteca, visto que a biblioteca funciona como uma parte independente da escola (tem seu próprio fluxo de trabalho e funcionários/responsabilidades separados. O sistema permite o cadastro de livros, a gestão de bibliotecários com diferentes níveis de acesso, a realização de aluguéis, renovações e devoluções.

A solução utiliza uma arquitetura baseada em microsserviços (embora contida neste repositório como um serviço core) e integra-se com serviços de notificação via mensageria.
## Tecnologias Utilizadas
- **Java 17** & **Spring Boot 3.3.4**
- **PostgreSQL** para persistência de dados.
- **Spring Security & JWT** para autenticação e autorização.
- **RabbitMQ** para envio de notificações assíncronas de aluguel e devolução.
- **MapStruct** para mapeamento entre Entidades e DTOs.
- **OpenFeign** para comunicação entre componentes/serviços.
- **OpenAPI / Swagger** para documentação dos endpoints.

## Módulos do Sistema

### 1. Livro
Responsável pelo catálogo de obras da biblioteca. Permite a inclusão, alteração, consulta e exclusão de livros.

### 2. Bibliotecário (Autenticação)
Gere os usuários do sistema que operam a biblioteca. Inclui diferentes níveis hierárquicos:
- `ORGANIZACAO`, `RECEPCAO`, `ADMINISTRACAO`, `ESTOQUE`, `GERENCIA`.
A segurança é baseada em tokens JWT, exigindo que o bibliotecário esteja autenticado para realizar operações críticas.

### 3. Aluguel
Controla o processo de saída de livros. 
- Valida se o livro está disponível.
- Registra a data de início e fim.
- Permite a renovação do prazo.
- Dispara notificações via RabbitMQ (`notificacoes.biblioteca.aluguel`).

### 4. Devolução
Registra o retorno dos livros.
- Finaliza o aluguel correspondente.
- Dispara notificações via RabbitMQ (`notificacoes.biblioteca.devolucao`).

### 5. Common (Infraestrutura)
Contém as configurações compartilhadas, filtros de segurança, tratamento global de exceções e configurações de mensageria.

## Como Executar
1. Certifique-se de ter o RabbitMQ rodando localmente (portas padrão).
2. Execute a aplicação via Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Acesse a Documentação Swagger: `http://localhost:8082/swagger-ui/index.html`

## Documentação da API
A API utiliza OpenAPI (Swagger). Ao rodar o projeto, a documentação interativa estará disponível, permitindo testar os endpoints e visualizar os esquemas de dados.
