# US2102 Protocolo de comunicação
=======================================


# 1. Requisitos

Como Gestor de Projeto, eu pretendo que as comunicações realizadas através do protocolo SDP2021 estejam protegidas.

# 2. Análise
**Pré-Análise** - É necessário estar implementado a aplicação de Serviços e RH .

Deve ser utilizado SSL/TLS com autenticação mútua baseada em certificados de chave pública para garantir que as comunicações na aplicação de Serviços e RH são seguras.

**Pós-Análise** - As comunicações entre aplicações estará protegida.

# 3. Design

Para responder a este problema foi usada a tecnologia SSL/TLS para que a comunicação entre as diversas aplicações esteja protegida por autenticação mútua. Para tal foi necessário gerar um certificado/key para a aplicação na aplicação de Serviços e RH que atua como cliente.


## 3.1. Realização da Funcionalidade
É necessário alterar as propriedades da aplicação indicando-lhe a localização do certificado do servidor como também a password para aceder a esse mesmo certificado.

	private static final String TRUSTED_STORE="SSL/client2_J.jks";
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
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1:** Verificar que não é possível criar uma instância da classe Exemplo com valores nulos.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}

# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*
