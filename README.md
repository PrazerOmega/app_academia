# FitTrack

Aplicativo Android para montar treinos de academia e registrar as sessões, feito em Kotlin e Jetpack Compose.

> 🚧 **Em desenvolvimento (Fase 1).** A estrutura do app, a navegação e o design system já estão prontos. A tela Início já mostra o layout do dashboard, mas ainda sem dados reais. As outras abas mostram só mensagens provisórias.

## Funcionalidades

O app tem cinco abas na barra de navegação inferior:

| Aba | Descrição |
|---|---|
| **Início** | Dashboard com treino em andamento, treino de hoje, último treino, frequência semanal e próximo treino |
| **Treinos** | Criação e gerenciamento da divisão de treinos |
| **Exercícios** | Biblioteca de exercícios |
| **Histórico** | Sessões de treino concluídas |
| **Perfil** | Preferências do usuário |

## Tecnologias

- **Kotlin** 2.4 e **Jetpack Compose** (Material 3)
- **Navigation Compose** com rotas tipadas (`kotlinx.serialization`)
- **ViewModel** + `StateFlow`, com estado de UI exposto por tela
- **Room** e **DataStore**, já configurados para guardar os dados das próximas fases
- Injeção de dependências manual (`AppContainer`), sem Hilt/Koin
- Testes: JUnit, Robolectric, Coroutines Test e Compose UI Test

## Requisitos

- Android Studio (versão recente, compatível com AGP 9.5)
- JDK 17
- Android 8.0 (API 26) ou superior para rodar o app

## Como rodar

```bash
git clone https://github.com/PrazerOmega/app_academia.git
cd app_academia
./gradlew installDebug
```

Você também pode abrir a pasta no Android Studio e usar **Run ▶**.

Para rodar os testes unitários:

```bash
./gradlew test
```

## Estrutura do projeto

```
app/src/main/java/com/fittrack/app/
├── core/
│   ├── designsystem/   # Tema (cores, tipografia, formas, espaçamentos) e componentes reutilizáveis
│   ├── di/             # Container de dependências manual
│   └── util/           # Formatadores de data, hora e duração
├── feature/
│   ├── home/           # Dashboard (tela, ViewModel, estado de UI)
│   ├── workouts/
│   ├── exercises/
│   ├── history/
│   └── profile/
├── navigation/         # Rotas, NavHost e destinos da barra inferior
├── FitTrackApplication.kt
└── MainActivity.kt
```

Cada funcionalidade fica em seu próprio pacote dentro de `feature/`. O que é compartilhado entre elas fica em `core/`.

## Roadmap

- [x] **Fase 1:** estrutura base, navegação, design system e layout do dashboard
- [ ] **Fase 2:** banco de dados (Room), DataStore e repositórios
- [ ] Cadastro de treinos e exercícios
- [ ] Registro de sessões de treino e histórico
- [ ] **Fase 5:** dashboard com dados reais
