# sw-planets-rest-api
Rest api para cadastro de planetas com integração a api https://swapi.co/


## API endpoints
           
      GET    |  /planets                          |  Lista de todos planetas                                        
      GET    |  /planets/search?id=x              |  Busca um planeta com o id 'x' (sendo x um número)            
      GET    |  /planets/search?nome=s            |  Busca um planeta com o nome 's' (sendo s um nome)
      DELETE |  /planets/delete?id=x              |  Deleta o planeta com o id 'x' (sendo x um número)                         
      POST   |  /planets/create                   |  Recebe um json com nome, clima e terreno e insere o planeta no banco
      
      



Foi utilizado o banco em memória, para mudar o banco basta mudar as configurações do arquivo application.properties que fica na pasta resources.
