- rest
	- controller
	- dto
	- mappers
- domain
	- model
		- entity
		- repository
	- service
		- impl
	- facade
		- impl
- core 
	- exceptions
	- base-class

O pacote rest.controller armazena as controller da aplicação e é responsável por 
O pacote rest.dto armazena objetos ques servem apenas como consumo de API e não devem ter valores modificados ou ser utilizado em outros níveis da aplicação
O pacote domain.model.entity armazena as entidades da aplicação
O pacote domain.model.repository armazena as classes de acesso ao banco de dados(utilizando o padrão repository)
O pacote domain.service armazena as interfaces que representam as classes onde serão tratadas as regras de negócio da aplicação
O pacote domain.service.impl armazena as implementações das classes onde são tratadas as regras de negócio da aplicação
O pacote domain.facade armazena as interfaces que representam classes que fazem as múltiplas chamadas e controle das classes de regra de negócio que envolvem o gerenciamento dos objetos 
O pacote domain.facade.impl armazena as implementações das classes que fazem as múltiplas chamadas e controle das classes de regra de negócio que envolvem o gerenciamento dos objetos

melhorar nome do pacote core