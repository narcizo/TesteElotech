# Teste técnico Grupo Irrah

Java 20\
Spring-boot 3.1.2\
PostgreSQL 15.3\
Angular 16.2

## Organização
Cada pasta contém a implementação de diferentes partes do teste, sendo elas: API com Java e Springboot, 
Frontend em Angular e teste de lógica.

A API e o Frontend foram Dockerizados enquanto o teste de lógica tem que ser rodado localmente.
Utilizei a IDE IntelliJ Community para a API e o teste de lógica e o VS Code para o Frontend.

## Docker

Para rodar o projeto com docker baixar as duas imagens do dockerhub.
[Backend](https://hub.docker.com/repository/docker/narcizo/teste_elotech_api_img/general) e
[Frontend](https://hub.docker.com/repository/docker/narcizo/teste_elotech_frontend_img/general)

Após baixar a imagem rodar o seguinte comando para subir os containers do PostgreSQL, SpringBoot e Angular:
```
cd src/main/docker
docker-compose up
cd ..
```

Após isso é possivel acessar a API através do endereço ```http://localhost:8081/<caminho-endpoint>``` e
acessar o Frontend pelo endereço ```http://localhost:4200```.

## Postman
Um arquivo JSON da collection do Postman que fiz para esse projeto está disponível na raiz do projeto com o nome
```Teste Elotech.postman_collection.json```.

## FrontEnd
Infelizmente não consegui implementar todo o Frontend para esse projeto no tempo
especificado, porém foi implementado o get paginado de Pessoa e Contato e uma interface básica.

## Dúvidas e contato
Quaisquer dúvidas sobre o projeto por favor me mandar mensagem no [LinkedIn](https://www.linkedin.com/in/narcizog/)
ou pelo meu email narcizo.gabriel2@gmail.com.