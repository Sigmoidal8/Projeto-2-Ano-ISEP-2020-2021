# US1006_Componente representativa da tarefa automática
=======================================


# 1. Requisitos

Como Gestor de Projeto, eu pretendo que seja desenvolvida a componente representativa de uma tarefa automática dedicada à execução de um script no âmbito de um pedido e que a mesma seja adicionada à biblioteca de atividades típicas do sistema para, dessa forma, poder ser usada na definição de fluxos de atividades.

A interpretação feita deste requisito foi no sentido de desenvolver no sistema a componente representativa de uma tarefa automática dedicada à execução de um script

# 2. Análise

**Pré-Análise** - É necessário a existência de colaboradores no sistema.

A componente representativa da tarefa automática irá funcionar como um mecanismo autónomo que irá proceder a execuçao de um script de uma tarefa clasificada como automática assim que esta for "chamada" para ser executada. Nesta componente o GSH deverá introduzir se será necessário para o serviço em questão a inclusão de uma tarefa de aprovação e se sim por quem esta deverá ser aprovada, pelo responsável hierárquico do colaborador solicitante ou pelo responsável pelo catálogo no qual este serviço está presente.

**Pós-Análise** - Será criado um workflow com as tarefas definidas.

# 3. Design

Para responder a este problema foi usado o padrão Controller para criar o controlador SpecifyServiceController. Este controlador é responsável pelo tratamento do processo de criação de uma novo serviço no sistema e como tal vai ter também a responsabilidade de criar o workflow do serviço. Este controlador por sua vez faz uso da classe Worklfow para criar uma instância de fluxo de atividades e da classe ApprovalTask para criar uma tarefa de aprovação e/ou AutomaticTask para criar uma tarefa automática. É também utilizado o padrão Builder para adicionar o workflow ao serviço.

## 3.1. Realização da Funcionalidade

*Nesta secção deve apresentar e descrever o fluxo/sequência que permite realizar a funcionalidade.*

## 3.2. Diagrama de Classes

*Nesta secção deve apresentar e descrever as principais classes envolvidas na realização da funcionalidade.*

## 3.3. Padrões Aplicados

Controler e Builder

## 3.4. Testes

**Teste 1:** Verificar que não é possível criar uma instância da classe AutomaticTask com valores nulos em script.

	@Test(expected = IllegalArgumentException.class)
		public void ensureRequestCantHaveNulScript() {
		ManualTask instance = new ManualTask(type, index, null);
	}


# 4. Implementação

*Nesta secção a equipa deve providenciar, se necessário, algumas evidências de que a implementação está em conformidade com o design efetuado. Para além disso, deve mencionar/descrever a existência de outros ficheiros (e.g. de configuração) relevantes e destacar commits relevantes;*

*Recomenda-se que organize este conteúdo por subsecções.*

# 5. Integração/Demonstração

*Nesta secção a equipa deve descrever os esforços realizados no sentido de integrar a funcionalidade desenvolvida com as restantes funcionalidades do sistema.*

# 6. Observações

*Nesta secção sugere-se que a equipa apresente uma perspetiva critica sobre o trabalho desenvolvido apontando, por exemplo, outras alternativas e ou trabalhos futuros relacionados.*
