# US3011Apresentar_Dashbord
=======================================


# 1. Requisitos

Como utilizador, eu pretendo que após me autenticar me seja apresentado o meu dashboard web e que este se mantenha atualizado (automaticamente).

A interpretação feita deste requisito foi no sentido de que o sistema mantenha o dashbord do utilizador sempre atualizado sem ser precisa a intervenção do mesmo para atualizar a pagina

# 2. Análise

**Pré-Análise** - É necessário a existência um conjunto de tarefas pendentes e assignadas a um determinadao colaborador que esta com sessão iniciado no sistema.

A atualização do dashboard deve ser feito automaticamente pelo sistema sem qualquer intervenção do utilizador. Esta atualização deve ser realizada sempre que algo relevante ao utilizador seja alterado no sistema ou o próprio sistema seja atualizado. No dashbord deve constar, entre outras dados, o número de tarefas pendentes do utilizador, quantas dessas tarefas já terminaram o prazo limite de resolução indicado no pedido e quantas terminam esse prazo em breve (e.g. em menos de 1 hora), a distribuição dessas tarefas pelos patamares de urgência e de criticidade. Este deve ser exibido imediatamente após autenticação do utilizador e manter-se sempre atualizado (e.g., ao ser-lhe, atribuída a realização de uma nova tarefa, o dashboard deve atualizar, sem intervenção do
utilizador, a quantidade de tarefas pendentes).

**Pós-Análise** - Será mostrado no dashboard um conjunto de tarefas pendentes de um colaborador especifico bem como todos os dados referentes a essas tarefas como a prioridade, datas de deadline, etc.

# 3. Design

Para responder a este problema foi usado o padrão Controller para criar o controlador DashboardController. Este controlador é responsável pelo tratamento dos dados de arranque da dashboard para que esta seja aberta no brower e o colaborador poder visualizar todos os seus dados referentes a essas mesmas tarefas.

## 3.1. Realização da Funcionalidade

*Nesta secção deve apresentar e descrever o fluxo/sequência que permite realizar a funcionalidade.*

## 3.2. Diagrama de Classes

*Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade.*

## 3.3. Padrões Aplicados

Controler e Repository

## 3.4. Testes

*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*


# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*
