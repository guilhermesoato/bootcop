# Rastreador de Sono - Monitor de Hábitos Diários

Aplicação desktop profissional desenvolvida com JavaFX para monitorar padrões de sono e rastrear hábitos diários, ajudando usuários a melhorar sua saúde e bem-estar.

**Versão:** 1.0.0 | **Status:** Pronto para Produção

---

## Índice

- [Declaração do Problema](#declaração-do-problema)
- [Visão Geral da Solução](#visão-geral-da-solução)
- [Público Alvo](#público-alvo)
- [Funcionalidades Principais](#funcionalidades-principais)
- [Pilha Tecnológica](#pilha-tecnológica)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Instalação](#instalação)
- [Executando a Aplicação](#executando-a-aplicação)
- [Executando Testes](#executando-testes)
- [Executando Análise Estática](#executando-análise-estática)
- [Pipeline CI/CD](#pipeline-cicd)
- [Guia de Uso](#guia-de-uso)
- [Contribuindo](#contribuindo)
- [Licença](#licença)
- [Autor](#autor)

---

## ⚠️ Aviso Importante - Local de Instalação

**NÃO clone ou instale este projeto em pastas sincronizadas com:**
- 📁 **OneDrive**
- 🔵 **Google Drive**
- ☁️ **Dropbox**
- 💾 Qualquer outro serviço de sincronização em nuvem

**Por quê?** Os serviços em nuvem podem interferir com:
- Arquivos temporários do Maven e compilação
- Arquivos de dados JSON
- Operações do Git
- Execução da aplicação JavaFX

**Solução:** Clone o repositório em uma pasta local do seu computador (ex: `C:\dev`, `/home/user/projects`, `Desktop`, etc.)

---

## Declaração do Problema

**O Problema Real:**

Muitas pessoas lutam contra padrões inconsistentes de sono e falta de consciência sobre seus hábitos diários. Isso resulta em:
- Privação de sono e má qualidade do sono
- Dificuldade em estabelecer rotinas saudáveis
- Falta de compreensão sobre como comportamentos afetam o sono
- Nenhuma forma sistemática de rastrear progresso ou identificar padrões

Indivíduos, especialmente estudantes, profissionais e idosos, precisam de uma ferramenta simples e intuitiva para:
- Registrar e monitorar duração e qualidade do sono
- Rastrear hábitos diários importantes (exercício, meditação, hidratação)
- Visualizar padrões e progresso
- Tomar decisões sobre saúde baseadas em dados

---

## Visão Geral da Solução

**Rastreador de Sono** é uma aplicação desktop leve que fornece:

1. **Gravação de Sono** - Registre duração e qualidade do sono diário
2. **Rastreamento de Hábitos** - Monitore hábitos diários importantes (exercício, meditação, hidratação)
3. **Análise Estatística** - Veja horas médias de sono e taxas de conclusão de hábitos
4. **Persistência de Dados** - Salve automaticamente todos os dados localmente

A aplicação usa uma interface gráfica limpa e intuitiva que não requer conhecimento técnico para usar. Os dados são armazenados localmente em arquivos JSON, garantindo privacidade e disponibilidade offline.

---

## Público Alvo

- **Profissionais** buscando melhor higiene do sono e equilíbrio trabalho-vida
- **Estudantes** gerenciando estresse e estabelecendo rotinas saudáveis
- **Idosos** e cuidadores monitorando padrões de sono para gestão de saúde
- **Atletas** otimizando recuperação e desempenho através do rastreamento de sono
- **Indivíduos conscientes com a saúde** construindo e mantendo hábitos positivos

---

## Funcionalidades Principais

### 1. Gerenciamento de Registros de Sono
- Adicione data, horas dormidas e classificação de qualidade do sono
- Valida automaticamente entrada (0-24 horas, níveis de qualidade válidos)
- Veja todos os registros históricos de sono em uma lista organizada
- Delete registros de sono específicos

### 2. Rastreamento de Hábitos Diários
- Crie entradas de hábitos customizados para qualquer dia
- Marque hábitos como completados ou incompletos
- Adicione notas opcionais para contexto
- Veja todas as entradas de hábitos organizadas por data

### 3. Painel de Estatísticas
- Calcule horas médias de sono (últimos 7 e 30 dias)
- Rastreie taxas de conclusão de hábitos
- Veja dicas úteis para melhorar o sono
- Atualizações de estatísticas em tempo real

### 4. Persistência de Dados
- Salvamento automático ao fechar a aplicação
- Opcao de salvamento manual
- Armazenamento local baseado em JSON (sem servidor necessario)
- Backup e transferencia faceis de dados

---

## Pilha Tecnologica

| Componente | Tecnologia | Versão |
|-----------|-----------|----------||
| **Linguagem** | Java | 21 LTS |
| **Ferramenta de Construção** | Maven | 3.9.14 |
| **Framework GUI** | JavaFX | 21.0.1 |
| **Testes** | JUnit 5 | 5.10.0 |
| **Qualidade de Código** | Checkstyle | 10.12.4 |
| **Assertions** | AssertJ | 3.27.7 |
| **Formato de Dados** | JSON (GSON) | 2.10.1 |
| **CI/CD** | GitHub Actions | - |

---

## Estrutura do Projeto

```
sleep-tracker/
├── src/
│   ├── main/
│   │   ├── java/com/habitsleep/
│   │   │   ├── SleepTrackerApp.java          # Ponto de entrada principal
│   │   │   ├── model/
│   │   │   │   ├── SleepRecord.java          # Modelo de dados de sono
│   │   │   │   ├── HabitEntry.java           # Modelo de dados de hábitos
│   │   │   │   └── HabitTracker.java         # Lógica de negócio principal
│   │   │   ├── ui/
│   │   │   │   └── SleepTrackerUI.java       # Implementação GUI com JavaFX
│   │   │   └── util/
│   │   │       └── DataPersistence.java      # Serialização/desserialização JSON
│   │   └── resources/                        # Recursos estáticos
│   └── test/
│       └── java/com/habitsleep/
│           └── model/
│               ├── SleepRecordTest.java      # Testes unitários para SleepRecord
│               ├── HabitEntryTest.java       # Testes unitários para HabitEntry
│               └── HabitTrackerTest.java     # Testes unitários para HabitTracker
├── .github/
│   └── workflows/
│       └── ci.yml                            # Pipeline CI do GitHub Actions
├── pom.xml                                   # Configuração Maven e dependências
├── checkstyle.xml                            # Regras de qualidade de código
├── .gitignore                                # Padrões de ignorar Git
├── README.md                                 # Documentação do projeto
└── LICENSE                                   # Licença MIT

```

---

## Instalação

### Pré-requisitos

- **Kit de Desenvolvimento Java (JDK) 21 ou superior**
  - Baixe em [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) ou use OpenJDK
  - Verifique: `java -version`

- **Maven 3.9 ou superior**
  - Baixe em [Site Oficial Maven](https://maven.apache.org/download.cgi)
  - Verifique: `mvn -v`

- **Git** (opcional, para clonar o repositório)
  - Baixe em [Site Oficial Git](https://git-scm.com/)

### Passos

1. **Clone o Repositório**
   ```bash
   git clone https://github.com/guilhermesoato/bootcop.git
   cd sleep-tracker
   ```

2. **Verifique Configuração do Maven**
   ```bash
   mvn clean compile
   ```
   Este comando vai:
   - Baixar todas as dependências
   - Compilar o código-fonte
   - Preparar o projeto para construção

3. **Construa o Projeto**
   ```bash
   mvn clean package
   ```
   Isso cria um arquivo JAR executável no diretório `target/`.

---

## Executando a Aplicação

### Opção 1: Execute run.bat (Recomendado no Windows)
```bash
cd diretório/do/projeto
run.bat
```

### Opção 2: Compile e Execute JAR
```bash
# Primeiro, construa o projeto
mvn clean package

# Depois execute o JAR
java -jar target/sleep-tracker-1.0.0-executable.jar
```

### Opção 3: Execute do IDE
- Importe o projeto no IntelliJ IDEA, Eclipse ou VS Code
- Certifique-se de que JDK 21+ está configurado como SDK do projeto
- Execute `SleepTrackerApp.java` como uma aplicação Java

### Primeiro Lançamento
No primeiro lançamento, a aplicação vai:
1. Criar um diretório `habit_tracker_data/` em sua pasta pessoal
2. Inicializar arquivos de dados vazios para registros de sono e hábitos
3. Exibir uma interface vazia pronta para entrada de dados

---

## Executando Testes

### Executar Todos os Testes
```bash
mvn clean test
```

### Executar Classe de Teste Específica
```bash
mvn test -Dtest=HabitTrackerTest
```

### Executar Testes com Relatório de Saída
```bash
mvn clean test -X
```

### Cobertura de Testes
O projeto inclui **28 testes unitários** cobrindo:

- **SleepRecord** (5 testes)
  - Criação válida de registro
  - Validação de entrada inválida (horas negativas, qualidade inválida)
  - Operações setter
  - Combinações de valores de qualidade

- **HabitEntry** (5 testes)
  - Criação e validação de entrada
  - Tratamento de nome de hábito vazio/nulo
  - Operações setter
  - Gerenciamento de notas

- **HabitTracker** (18+ testes)
  - Operações adicionar/remover
  - Cálculo de sono médio
  - Cálculo de taxa de conclusão de hábitos
  - Filtragem por data e estatísticas
  - Casos extremos (sem registros, parâmetros inválidos)

### Resultados de Testes
Todos os testes devem passar com saída como:
```
[INFO] Tests run: 28, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

---

## Executando Análise Estática

### Verificar Qualidade de Código
```bash
mvn checkstyle:check
```

### Gerar Relatório Detalhado
```bash
mvn checkstyle:checkstyle
```
Relatório é gerado em: `target/site/checkstyle.html`

### Regras Implementadas
- **Convenções de Nomenclatura**: Classes (PascalCase), métodos/variáveis (camelCase)
- **Documentação**: Javadoc para classes e métodos públicos
- **Espaçamento**: Espaçamento consistente e formatação
- **Importações**: Sem importações com asterisco, remoção de importações não utilizadas
- **Complexidade**: Complexidade ciclomática limitada a 10 por método
- **Comprimento de Linha**: Máximo de 120 caracteres
- **Organização de Código**: Estrutura apropriada e legibilidade

### Corrigindo Violações
A maioria das violações pode ser corrigida com:
- Convenções de nomenclatura apropriadas
- Adicionar comentários Javadoc
- Ajustar espaçamento
- Simplificar métodos complexos

---

## Pipeline CI/CD

### Workflow do GitHub Actions

O projeto inclui um pipeline de CI automatizado que executa em:
- **Quando:** Todo push para branch `main`
- **Quando:** Todo pull request

### Estágios do Pipeline

```
1. Configuração do Ambiente
   - Instalar JDK 21
   - Verificar instalação Maven

2. Construção
   - Limpar e compilar fontes
   - Resolver todas as dependências

3. Qualidade de Código
   - Executar linting com Checkstyle
   - Gerar relatórios de qualidade

4. Teste
   - Executar todos os testes unitários
   - Gerar relatórios de testes

5. Empacotamento
   - Criar JAR executável
   - Verificar artefatos de construção
```

### Visualizando Status do Pipeline

1. Vá para seu repositório no GitHub
2. Clique na aba **Actions**
3. Selecione a execução do workflow
4. Veja logs e resultados detalhados

---

## Guia de Uso

### Iniciando a Aplicação

1. Lance a aplicação usando um dos métodos acima
2. A janela principal exibe três abas: Registros de Sono, Hábitos Diários e Estatísticas

### Aba Registros de Sono

**Adicionando um Registro de Sono:**
1. Selecione uma data no seletor de datas
2. Digite horas dormidas (0-24 horas)
3. Selecione qualidade do sono: "ruim", "razoável", "bom" ou "excelente"
4. Clique em **Adicionar Registro de Sono**
5. Confirme a mensagem de sucesso

**Visualizando Registros:**
- Todos os registros aparecem na lista abaixo
- Formato: `YYYY-MM-DD - X.X horas (qualidade)`

**Deletando Registros:**
1. Selecione um registro da lista
2. Clique em **Deletar Selecionado**
3. Confirme a deleção

### Aba Hábitos Diários

**Adicionando uma Entrada de Hábito:**
1. Selecione uma data (padrão: hoje)
2. Digite nome do hábito (ex: "Exercício", "Meditação", "Hidratação")
3. Marque "Completado" se o hábito foi realizado esse dia
4. Adicione notas opcionais para contexto
5. Clique em **Adicionar Entrada de Hábito**

**Visualizando Entradas:**
- Todas as entradas aparecem na lista
- Formato: `[V/X] YYYY-MM-DD - NomeHábito (notas)`

**Rastreando Múltiplos Hábitos:**
- Crie entradas de hábitos diferentes para atividades diferentes
- Rastreie o mesmo hábito em múltiplos dias
- Status de conclusão diferente para dias diferentes

### Aba Estatísticas

**Visualize Seu Progresso:**
1. Clique no botão **Atualizar Estatísticas**
2. Veja horas médias de sono para:
   - Últimos 7 dias
   - Últimos 30 dias

3. Clique no botão **Atualizar Stats de Hábitos**
4. Veja percentuais de conclusão de hábitos (ex: "Exercício: 75%")

**Dicas de Sono:**
- A aba exibe recomendações baseadas em evidências para melhor sono

### Persistência de Dados

- Todos os dados são **salvos automaticamente** ao fechar a aplicação
- Os dados são armazenados localmente em pasta `habit_tracker_data/` como JSON
- Nenhuma conexão com internet é necessária
- Faça backup da pasta `habit_tracker_data/` para segurança

---

## Contribuindo

Bem-vindo a contribuições! Siga estes passos:

1. **Faça Fork do Repositório**
   ```bash
   git clone https://github.com/guilhermesoato/bootcop.git
   ```

2. **Crie uma Branch de Feature**
   ```bash
   git checkout -b feature/nome-da-sua-feature
   ```

3. **Faça Suas Mudanças**
   - Siga diretrizes de estilo de código
   - Adicione testes para novas funcionalidades
   - Atualize documentação

4. **Execute Verificações de Qualidade**
   ```bash
   mvn clean package
   ```
   Certifique-se de que todos os testes passam e linting teve sucesso.

5. **Commit e Push**
   ```bash
   git add .
   git commit -m "feat: Adicione descrição da sua feature"
   git push origin feature/nome-da-sua-feature
   ```

6. **Envie um Pull Request**
   - Descreva suas mudanças claramente
   - Referencie qualquer issue relacionada
   - Certifique-se de que pipeline de CI passa

### Diretrizes de Estilo de Código
- Siga convenções de nomenclatura Java
- Use camelCase para métodos e variáveis
- Use PascalCase para classes
- Adicione Javadoc para métodos públicos
- Mantenha métodos focados e simples
- Complexidade ciclomática máxima: 10

---

## Licença

Este projeto está licenciado sob a **Licença MIT** - veja arquivo [LICENSE](LICENSE) para detalhes.

Você tem liberdade para:
- Usar comercialmente
- Modificar o código
- Distribuir
- Usar privativamente

Sob a condição de incluir uma cópia da licença.

---

## Autor

**Guilherme Soato**
- GitHub: [@guilhermesoato](https://github.com/guilhermesoato)
- Email: guilhermesoato7@gmail.com

---

## Recursos de Aprendizado

Este projeto demonstra:
- Práticas profissionais de desenvolvimento Java
- Princípios de design orientado a objetos
- Desenvolvimento GUI com JavaFX
- Testes unitários com JUnit 5
- Análise de qualidade de código com Checkstyle
- Automação de CI/CD com GitHub Actions
- Controle de versão com Git
- Versionamento semântico
- Documentação profissional

---
