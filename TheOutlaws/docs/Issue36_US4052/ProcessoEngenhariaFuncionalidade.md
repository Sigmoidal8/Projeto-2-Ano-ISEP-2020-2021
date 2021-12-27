# US4052 Protocolo de comunicação
=======================================


# 1. Requisitos

Como Gestor de Projeto, eu pretendo que as comunicações realizadas como aplicação cliente através do protocolo SDP2021 estejam protegidas.

# 2. Análise

**Pré-Análise** - É necessário estar implementada a comunicação entre aplicações cliente e servidora.

Deve ser utilizado SSL/TLS com autenticação mútua baseada em certificados de chave pública para garantir que as comunicações com a aplicação cliente são seguras.

**Pós-Análise** - As comunicações entre aplicações estará protegida.


# 3. Design
Para resolver este requisito foi usada a tecnologia SSL/TLS para que a comunicação feita entre as várias aplicações esteja protegida por autenticação mútua. Para tal foram geradas certificados/keys para cada aplicação quando trabalha como cliente ou servidora.

Desta forma não é possível fazer ligações aos servidores se o cliente não tiver uma chave registada neste.


## 3.1. Realização da Funcionalidade

É necessário alterar as propriedades da aplicação indicando-lhe a localização do certificado do cliente como também a password para aceder a esse mesmo certificado.

	private static final String TRUSTED_STORE="SSL/clientX_J.jks";
	private static final String KEYSTORE_PASS="forgotten";

	// Trust these certificates provided by authorized clients
	System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
	System.setProperty("javax.net.ssl.trustStorePassword",KEYSTORE_PASS);

	// Use this certificate and private key as server certificate
	System.setProperty("javax.net.ssl.keyStore",TRUSTED_STORE);
	System.setProperty("javax.net.ssl.keyStorePassword",KEYSTORE_PASS);

## 3.2. Diagrama de Classes

*Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade.*

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas.*

## 3.4. Testes
Para testar esta funcionalidade é necessário correr o programa como um cliente não permitido pelo servidor, quando isto acontece o servidor apresenta que o certificado do cliente é desconhecido através do erro certificate_unknown como podemos ver abaixo:

![SSL](SSL.png)

Se a conexão for feita por um cliente válido com um certificado a operação corre segundo planeado e no exemplo que usamos a informação que o cliente pede ao servidor é enviada pelo servidor:

![server](server.png)

![dashboard](dashboard.png)

# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*
