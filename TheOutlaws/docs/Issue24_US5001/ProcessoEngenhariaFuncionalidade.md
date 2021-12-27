# US5001_Executor de tarefas automáticas

=======================================


# 1. Requisitos

**UC 4.3.2** Como Gestor de Projeto, eu pretendo que seja desenvolvido o Executor de Tarefas Automáticas.
 * Deve ser usado o protocolo de aplicação fornecido (SDP2021).
 * Deve ser suportado o processamento simultâneo de pedidos para execução de tarefas automáticas.
 * Neste sprint, para efeitos de demonstração, é aceitável que a execução das tarefas automáticas seja apenas simulada (mock).

A interpretação feita deste requisito foi no sentido de serem realizadas tarefas com o carácter automático sem intervenção de nenhum utilizador.

# 2. Análise

**Pré-Análise:**
- A aplicação terá de ser capaz de comunicar entre Motor de Fluxo de Atividades e o Executor de Tarefas Automáticas.
- É necessária a existência de tarefas de carácter automático para poderem ser executadas pelo sistema.  

O sistema irá procurar as tarefas registadas como automáticas prontas a ser realizadas e realiza-las sem a intervenção de nenhum utilizador, sendo executada apenas pelo Executor de Tarefas Automáticas.
A partilha de informação é assegurada pela pela utilização uma mensagem de pedido, seguido da receção de uma mensagem de resposta. Estas transações são realizadas através de uma ligação TCP, estabelecida segundo o modelo cliente-servidor.

Para identificar-mos o tipo de pedido/resposta entre as aplicações seguimos a seguinte tabela:

| Código  | Significado |
|---------|-------------|
| 0       | Teste - Pedido de teste sem qualquer efeito para além da devolução de uma resposta com código 2. Este pedido não transporta dados |
| 1       | Fim - Pedido de fim de ligação. O servidor deve devolver uma resposta com código 2, após o que ambas as aplicações devem fechar a ligação TCP |
| 2       | Entendido - Resposta vazia (não transporta dados) que acusa a receção de um pedido. É enviada em resposta a pedidos com código 0 e código 1, mas poderá ser usada em outros contextos |
| 5       | Envia os dados do Fluxo de Atividades para o Executor de Tarefas Automáticas |
| 6       | Envia os dados para a dashbord |
| 10      | Solicita os dados relativos ao estado do Fluxo de Atividades |
| 11      | Solicita os dados relativos à dashbord |
| 255     | Segmento - identifica os dados transportados como sendo uma parte de um conjunto de dados mais extenso. Este código é usado para transferir volumes de dados superiores a 255 bytes. Nesse cenário um pedido ou uma resposta pode ser constituído por uma sequência de mensagens com código 255 finalizada por uma mensagem contendo um código diferente de 255. |

**Pós-Análise:**
- Tarefas pendentes com um carácter automático serão realizadas sem intervenção de nenhum colaborador.

# 3. Design


## 3.1. Realização da Funcionalidade


## 3.2. Diagrama de Classes


## 3.3. Padrões Aplicados


## 3.4. Testes
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*



# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*
