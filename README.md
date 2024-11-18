Sistema de Gerenciamento de Filmes
Este projeto é uma API REST desenvolvida em Spring Boot para gerenciar filmes, diretores e atores. A API permite criar, consultar, atualizar e excluir registros relacionados a essas entidades, além de oferecer suporte a edição especial de filmes.

IMPORTANTE!
O projeto possui um arquivo Loader.java que pré-carrega alguns registros de filmes, diretores, atores e edições especiais para o banco de dados.
Este Loader lê um arquivo CSV que se encontra no diretório "src\main\resources" e será executado durante o build do projeto!

Funcionalidades:

    Filmes:
        Adicionar novos filmes
        Listar todos os filmes
        Buscar filme por ID
        Atualizar informações de um filme
        Excluir um filme

    Diretores:
        Adicionar novos diretores
        Listar todos os diretores
        Buscar diretor por nome
        Excluir um diretor

    Atores:
        Adicionar novos atores
        Listar todos os atores
        Excluir um ator

    Edição Especial de Filmes:
        Contagem de edições especiais
        Listar todas as edições especiais

Tecnologias Utilizadas:

    Java 17
    Spring Boot
    Hibernate/JPA
    H2 Database (banco de dados em memória)
    Swagger/OpenAPI (documentação da API)

Estrutura dos Endpoints:

    Filmes (/api/films)
        POST /api/films - Adicionar um novo filme
        GET /api/films - Listar todos os filmes
        GET /api/films/{id} - Buscar filme por ID
        PUT /api/films/{id} - Atualizar um filme existente
        DELETE /api/films/{id} - Excluir um filme

    Diretores (/api/directors)
        POST /api/directors - Adicionar um novo diretor
        GET /api/directors - Listar todos os diretores
        GET /api/directors/name/{name} - Buscar diretor por nome
        DELETE /api/directors/{id} - Excluir um diretor

    Atores (/api/actors)
        POST /api/actors - Adicionar um novo ator
        GET /api/actors - Listar todos os atores
        DELETE /api/actors/{id} - Excluir um ator

    Edição Especial de Filmes (/api/specialEditionFilms)
        POST /api/specialEditionFilms - Adicionar uma edição especial de filme
        GET /api/specialEditionFilms - Listar todas as edições especiais
        GET /api/specialEditionFilms/count - Obter a contagem de edições especiais
        GET /api/specialEditionFilms/{id} - Buscar edição especial por ID
        PUT /api/specialEditionFilms/{id} - Atualizar uma edição especial
        DELETE /api/specialEditionFilms/{id} - Excluir uma edição especial

Testando a API:

    Utilize ferramentas como Postman ou cURL para enviar requisições HTTP para os endpoints mencionados.
    Como alternativa, é possível utilizar a UI do Swagger para testes dos endpoints.
    Insira os dados no formato JSON conforme necessário.

Em caso de dúvidas, entre em contato. 🚀
