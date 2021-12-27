# US4002_Disponibilização dos dados necessários

=======================================


# 1. Requisitos

**UC 4.3.4** Como Gestor de Projeto, eu pretendo que o Motor de Fluxo de Atividades disponibilize, a pedido, os dados necessários às aplicações "Serviços e RH" e "Portal dos Utilizadores".
* Deve ser usado o protocolo de aplicação fornecido (SDP2021).
* Deve ser suportado o processamento simultâneo de pedidos.
* Sugere-se que desde já seja considerada a adoção de mecanismos concorrente (e.g. threads) e partilha de estado entre esses mecanismos.
* Neste sprint, para efeitos de demonstração, é aceitável que o processamento associado a algumas destas comunicações seja apenas simulado (mock).

A interpretação feita deste requisito foi no sentido da informação relativa às atividades em curso ser facilmente disponibilizada ao utilizador.

# 2. Análise

**Pré-requisitos:**
- A aplicação terá de ser capaz de criar localmente um servidor HTTP, acessível apenas pelo localhost, e abre o URL respetivo onde mostra o dashboard web do utilizador que se autenticou na consola e o estado atual do motor de fluxo de atividades. A informação disponibilizada é obtida por comunicação entre o servidor HTTP e o Motor de Fluxo de Atividades.
- Para a construção da dashbord do utilizador é necessário a existência um conjunto de tarefas pendentes e assignadas a um determinado colaborador.

A monitorização e disponibilidade de toda a informação deve ser executada de maneira automática pelo sistema de modo a que seja possível aceder ao resumo da informação relativa a processos pendentes assim como a informação necessária para a construção da dashboard do utilizador. Será disponibilizada o número de tarefas pendentes do utilizador, quantas dessas tarefas já terminou o prazo limite de resolução indicado no pedido e quantas terminam esse prazo em breve, a distribuição dessas tarefas pelos patamares de urgência e de criticidade.
A partilha de informação é assegurada pela pela utilização uma mensagem de pedido, seguido da receção de uma mensagem de resposta. Estas transações são realizadas através de uma ligação TCP, estabelecida segundo o modelo cliente-servidor.

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

**Pós-requisitos**
- Será mostrado no dashboard um conjunto de tarefas pendentes de um colaborador especifico bem como todos os dados referentes a essas tarefas assim como o estado atual do motor de fluxo de atividades.

# 3. Design


## 3.1. Realização da Funcionalidade


## 3.2. Diagrama de Classes



## 3.3. Padrões Aplicados


## 3.4. Testes


# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*
