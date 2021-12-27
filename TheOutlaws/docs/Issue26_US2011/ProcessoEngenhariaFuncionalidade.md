# US2011_Conhecer_estado_do_fluxo_atividades
=======================================


# 1. Requisitos

**UC 4.1.1** Como GSH, eu pretendo conhecer o estado atual do motor de fluxos de atividades e que este se mantenha atualizado(automaticamente).

A interpretação feita deste requisito foi no sentido de informar ao gestor de serviços help desk qual o estado do fluxo de atividades em tempo real.

# 2. Análise
**Pré-Análise** - É necessário um GSH ter dado login na aplicação

A consulta do estado atual do fluxo deve ser efetuado manualmente pelo GSH e deverá abrir numa página web e deverá apresentar o estado das solicitações em aprovação e/ou resolução.

A página web deverá ser mantida atualizada sem recarregar.

**Pós-Análise** - Será mostrado uma dashboard com o estado dos pedidos existentes no programa, mostrando diversos dados como, código do pedido, data do pedido, deadline do pedido, etc.

# 3. Design

Para responder a este problema foi usado o padrão Controller para criar o controlador WorkflowDashboardController. Este controlador é responsável pelo tratamento dos dados de arranque da dashboard para que esta seja aberta no browser e o GSH poder visualizar todos os seus dados referentes a esses mesmos pedidos. Este controlador é também responsável pela comunicação com o servidor de fluxos de atividades visto que é este que com ele comunica via sockets.

## 3.1. Realização da Funcionalidade

*Nesta secção deve apresentar e descrever o fluxo/sequência que permite realizar a funcionalidade.*


## 3.2. Diagrama de Classes

*Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade.*

## 3.3. Padrões Aplicados

*Nesta secção deve apresentar e explicar quais e como foram os padrões de design aplicados e as melhores práticas.*

## 3.4. Testes
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*


# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*
