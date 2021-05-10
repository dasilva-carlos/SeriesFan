# Series Fan

Aplicativo para pesquisar sobre séries e seus episódios.

Utiliza a API: [TV Maze](https://www.tvmaze.com/api)

## Aparência

![Tela principal](/screenshots/Screenshot_1)
![Pesquisa](/screenshots/Screenshot_2)
![Sobre a série](/screenshots/Screenshot_3)
![Sobre o episódio](/screenshots/Screenshot_3)

## Estrutura

O projeto é baseado em um modelo de MVVM simplificado.

Ele possui apenas uma activity e a navegação entre Fragments é feita por meio das ferramentas de Jetpack 

A View Model provém LiveData's que são consumidos por Fragments e as tarefas assíncronas são feitas a partir de Coroutines.

Flow é utilizado para ajudar no mapeamento e controle dessas tarefas assíncronas.

O projeto é separado nos seguintes packages:

* _network_ - Todas as estruturas relacionados com o consumo de API's online
* _structure_ - Classes importantes para a aplicação Android
* _ui_ - Fragments e View Models e Adapters
* _customview_ - Views customizadas para o projeto
* _domain_ - View Objects e classes anexas
* _navigation_ - Lógica de navegação entre telas
* _utils_ - Funções genéricas

## Principais tecnologias / dependências utilizadas:

* Requisições Rest: Retrofit 2, Gson
* Estruturação: Jetpack Lifecycle/ViewModel, Coroutines, Flow
* Navegação: Jetpack Navigation
* Gerenciamento de imagens: Glide
* Injeção de dependência: Koin
* Layout: View Binding
* Paginação: Jetpack Paging 3
* Logging: Timber
* Verificação de estilo de código: ktlint

## Verificação de estilo de código

Para verificar se seu estilo de código está conforme os padrões do projeto, execute a task do gradle:

`verification/ktlint`

Ou execute no terminal:
```
./gradlew ktlint
```


Para realizar correções automáticas de estilo de código, execute:

`formatting/ktlintFormat`

Ou no terminal:
```
./gradlew ktlintFormat
```

## TO-DO's

* Escrita e configuração de testes unitários / instrumentados.
* Melhorar acessibilidade: marcar textos como título, incluir descrição de botões em elementos clicáveis, realizar testes de usabilidade.

