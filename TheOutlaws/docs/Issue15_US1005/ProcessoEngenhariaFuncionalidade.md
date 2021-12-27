# US1005_Componente_Representativa_Tarefa_Manual
=======================================


# 1. Requisitos

**UC 4.3.3a** Como Gestor de Projeto, eu pretendo que seja desenvolvida a componente representativa de uma tarefa manual dedicada à apresentação e recolha de informação a/de um utilizador no âmbito de um pedido e que a mesma seja adicionada à biblioteca de atividades típicas do sistema para, dessa forma, poder ser usada na definição de fluxos de atividades.

A interpretação feita deste requisito foi no sentido de especificar no sistema uma componente para a especificação de fluxo de atividades, neste caso para tarefas manuais.

# 2. Análise

**Pré-Análise** - É necessário a existência de colaboradores no sistema e equipas tal como deverá existir pelo menos um catálogo.

A componente representativa da tarefa manual irá servir como uma interface que irá aparecer quando o GSH(Gestor de Serviços Helpdesk) estiver a especificar um fluxo de atividades. Nesta componente o GSH deverá introduzir se será necessário para o serviço em questão a inclusão de uma tarefa de aprovação e se sim por quem esta deverá ser aprovada, pelo responsável hierárquico do colaborador solicitante ou pelo responsável pelo catálogo no qual este serviço está presente. De seguida deverá introduzir qual o tipo que deseja introduzir para que no futuro se realize a tarefa, por colaborador ou por equipas e seleccioná-los(as). Após este procedimento deverá introduzir o formulário da tarefa.

**Pós-Análise** - Será criado um workflow com as tarefas definidas.

# 3. Design

Para responder a este problema foi usado o padrão Controller para criar o controlador SpecifyServiceController. Este controlador é responsável pelo tratamento do processo de criação de uma novo serviço no sistema e como tal vai ter também a responsabilidade de criar o workflow do serviço. Este controlador por sua vez faz uso da classe Worklfow para criar uma instância de fluxo de atividades e da classe ApprovalTask para criar uma tarefa de aprovação e/ou ManualTask para criar uma tarefa manual. É também utilizado o padrão Builder para adicionar o workflow ao serviço.

## 3.1. Realização da Funcionalidade

*Nesta secção deve apresentar e descrever o fluxo/sequência que permite realizar a funcionalidade.*

## 3.2. Diagrama de Classes

*Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade.*

## 3.3. Padrões Aplicados


## 3.4. Testes
*Nesta secção deve sistematizar como os testes foram concebidos para permitir uma correta aferição da satisfação dos requisitos.*

**Teste 1 a 3:** Verificar que não é possível criar uma instância da classe ManualTask com valores nulos em form e em teams ou collaborators, dependendo da opção escolhida.

	@Test(expected = IllegalArgumentException.class)
		public void ensureRequestCantHaveNull<xField>() {
		ManualTask instance = new ManualTask(every field not null except xField);
	}

# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*
