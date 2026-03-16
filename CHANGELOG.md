# Histórico de Mudanças

Todas as mudanças notáveis neste projeto são documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/), e este projeto segue [Versionamento Semântico](https://semver.org/lang/pt-BR/).

---

## [1.0.0] - 2026-03-16

### Adicionado
- Sistema completo de rastreamento de sono com armazenamento JSON
- Interface GUI com JavaFX com abas para Registros de Sono, Hábitos Diários e Estatísticas
- Funcionalidade de Rastreamento de Hábitos com suporte a notas e status de conclusão
- Painel de Estatísticas mostrando sono médio e taxas de conclusão de hábitos
- 28 testes unitários automatizados cobrindo modelo de negócio
- Configuração de Checkstyle para análise estática de código
- Pipeline de Integração Contínua com GitHub Actions executando linting e testes
- Documentação README completa em português com guias de instalação e uso
- Arquivo LICENSE com Licença MIT
- Arquivo CHANGELOG com histórico de mudanças

### Características Principais
- Gravação e gerenciamento de registros de sono com validação
- Rastreamento customizado de hábitos diários
- Calculadora de sono médio para últimos 7 e 30 dias
- Persistência automática de dados em arquivos JSON
- Validação de entrada para todas as operações
- Interface intuitiva e amigável

### Dependências
- Java 21 LTS
- JavaFX 21.0.1
- JUnit 5.10.0
- AssertJ 3.27.7
- GSON 2.10.1
- Checkstyle 10.12.4

### Segurança
- Atualização de AssertJ para 3.27.7 para corrigir CVE-2026-24400 (XXE)

### Status
- Pronto para produção
- Pipeline de CI/CD funcionando
- Cobertura de testes: 28 testes
- Análise estática: 100% compliance

---

Notas de Versão

v1.0.0 representa a primeira versão estável de produção do Rastreador de Sono com todos os recursos principais, testes completos e automação de qualidade.

Para detalhes sobre upgrades técnicos, consulte a documentação do projeto README.md.
