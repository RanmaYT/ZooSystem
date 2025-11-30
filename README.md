# Animal Watch :kangaroo:
Sistema de informação interativo para visitantes de zoológico.


## Visão Geral :pushpin:
### Contexto
O sistema seria utilizado em painéis distribuídos por todo o perímetro do zoológico. Os visitantes poderiam acessá-lo para obter informações sobre os animais presentes em cada área.

### Objetivo
Facilitar o acesso e centralizar as informações sobre os animais para os visitantes.


## Funcionalidades e Atores :hammer:
### Visitantes :bust_in_silhouette:
- Consultar informações do animal
- Relatar erro nas informações do animal.

### Administrador :technologist:
- Consultar informações do animal.
- Adicionar novos animais.
- Atualizar informações de animais.
- Excluir animais.
- Visualizar relatos.


## Arquitetura MVC :open_file_folder:
Essa arquitetura foi escolhida com o intuito de diminuir o acoplamento, aprimorar a aplicação dos princípios SOLID e tornar o sistema mais organizado.

### Model 
O módulo model abrange tanto as classes de dados (Animal e Relato) quanto as classes responsáveis pela lógica de serviços (AnimalService e RelatoService).

### View 
A view é responsável exclusivamente pela exibição das informações na interface, sejam menus previamente definidos ou objetos de dados apresentados no formato de DTO.

### Controller 
A função principal do controller é atuar como intermediário entre a view e o model. Ele recebe as informações após sua exibição pela view e as repassa ao model.


## Documentação Oficial :page_with_curl:
Toda a documentação oficial do projeto está centralizada no Notion, garantindo organização, fácil acesso e atualização contínua das informações.

🔗 [Acesse aqui a documentação completa no Notion.](https://www.notion.so/Animal-Watch-27d0b3a5dbc780ea8d02c47c7caa1beb?source=copy_link)


## Tecnoligias Utilizadas :books:
#### Persistência de dados:
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18.1--1-316192?logo=postgresql&logoColor=white&labelColor=316192)
- ![JDBC](https://img.shields.io/badge/JDBC-42.7.8-316192?logo=postgresql&logoColor=white&labelColor=316192)

#### Desenvolvimento 
- ![Java](https://img.shields.io/badge/Java-17.0.15-f89820?logo=coffeescript&logoColor=white&labelColor=f89820)

#### Diagrama de classes e casos de uso
- ![PlantUML](https://img.shields.io/badge/PlantUML%20-90EE90?logo=plantuml&logoColor=white&labelColor=90EE90&color=90EE90)

#### Versionamento
- ![Git](https://img.shields.io/badge/Git-F05032?logo=git&logoColor=white)
- ![GitHub](https://img.shields.io/badge/GitHub-000000?logo=github&logoColor=white&labelColor=000000)

> Status do Projeto: Em desenvolvimento :white_check_mark:
