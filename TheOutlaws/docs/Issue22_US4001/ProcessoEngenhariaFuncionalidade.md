# US4001_Desenvolvimento do mecanismo de gestão/controlo/avanço


=======================================


# 1. Requisitos

Como Gestor de Projeto, eu pretende que seja desenvolvido no Motor de Fluxo de Atividade o mecanismo de gestão/controlo/avanço do fluxo de atividades de um dado pedidos.

# 2. Análise

**Pré-Análise** - Devem existir já criados pedidos e tarefas.

Caso o Motor de Fluxo de Atividade encontrar uma atividade automática, deve solicitar e monitorizar a execução desta pelo Executor de Tarefas Automáticas.
O Motor de Fluxo de Atividades vai percorrendo os pedidos, e gere o estado destes(dependendo do estado das suas atividades, pode estar em submetido, em aprovação, aprovado ,rejeitado ,em resolução, concluído ). Se um pedido se encontrar no estado de "submetido", o mecanismo irá gerir os estados das suas atividades(ativa, completada, em espera). Caso sejam automáticas, é chamado o executor de tarefas automáticas.

# 3. Design

*Nesta secção a equipa deve descrever o design adotado para satisfazer a funcionalidade. Entre outros, a equipa deve apresentar diagrama(s) de realização da funcionalidade, diagrama(s) de classes, identificação de padrões aplicados e quais foram os principais testes especificados para validar a funcionalidade.*

*Para além das secções sugeridas, podem ser incluídas outras.*

## 3.1. Realização da Funcionalidade

*Nesta secção deve apresentar e descrever o fluxo/sequência que permite realizar a funcionalidade.*

## 3.2. Diagrama de Classes

*Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade.*

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas.*

## 3.4. Testes
Não existe qualquer tipo de teste/verificação possível neste UC.

# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*
