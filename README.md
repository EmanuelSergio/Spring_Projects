# Voll.med API

## 📄 Descrição do Projeto
Esta é uma API REST desenvolvida em **Java** com **Spring Boot** para o gerenciamento de uma clínica médica fictícia chamada **Voll.med**. O sistema permite:

- Cadastro e gerenciamento de **médicos** e **pacientes**
- Agendamento e cancelamento de **consultas médicas**
- Controle de acesso via autenticação JWT
- Regras de negócio bem definidas para o funcionamento da clínica

---

## ✅ Funcionalidades Implementadas

### 🔐 Autenticação e Segurança
- Login com **JWT (JSON Web Token)**
- Proteção de endpoints com **Spring Security**
- Controle de perfis de usuário (admin, médico, paciente)

### 👨‍⚕️ Gestão de Médicos
- Cadastro com validação de dados
- Listagem paginada com filtros
- Atualização de dados cadastrais
- Exclusão lógica (inativação)
- Busca por **especialidade**

### 🧍‍♂️ Gestão de Pacientes
- Cadastro completo com endereço
- Listagem paginada
- Atualização de dados
- Exclusão lógica (inativação)

### 📅 Agendamento de Consultas
- Regras de negócio para marcação de consulta
- Escolha de médico específico ou alocação automática por especialidade
- Cancelamento com motivo e regras de antecedência
- Validações como horário de funcionamento, disponibilidade do médico, etc.

### 🗃 Persistência de Dados
- **MySQL** como banco de dados
- **Liquibase** para versionamento de banco
- **Spring Data JPA** para persistência

---

## 🛠 Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- Spring Security
- Liquibase
- MySQL
- JWT Authentication
- Maven
- Lombok

---

## 💡 Possíveis Implementações Futuras

### 📘 Documentação da API
- Swagger/OpenAPI

### 📊 Dashboard Administrativo
- Estatísticas de atendimentos e desempenho médico
- Relatórios gerenciais

### ⭐ Sistema de Avaliação
- Avaliações e feedbacks dos pacientes

### 🏥 Histórico Médico
- Prontuário eletrônico
- Histórico de consultas

### 🔔 Notificações
- Lembretes por email/SMS
- Confirmações de agendamento

### 💳 Integração com Pagamentos
- Pagamentos online
- Gestão financeira

### ⚙️ Infraestrutura
- Docker e deploy em containers
- CI/CD com GitHub Actions
- Monitoramento com Prometheus/Grafana

### 🧪 Testes Automatizados
- Unitários, integração e carga

### 🌐 Interface Web/Mobile
- Frontend para médicos e pacientes
- App para agendamento de consultas

---

## ▶️ Como Executar o Projeto

1. Clone este repositório:
```bash
git clone https://github.com/EmanuelSergio/Spring_Projects.git
```

2. Configure o banco MySQL no `application.properties`

3. Instale as dependências:
```bash
mvn install
```

4. Execute a aplicação:
```bash
mvn spring-boot:run
```

A API estará disponível em: **http://localhost:8080**

---

## 🔗 Endpoints Principais

- `POST /login` - Autenticação
- `GET/POST /medicos` - Gestão de médicos
- `GET/POST /pacientes` - Gestão de pacientes
- `POST /consultas` - Agendamento
- `DELETE /consultas` - Cancelamento

---

📌 Projeto desenvolvido para fins de aprendizado e prática com o ecossistema **Spring**.