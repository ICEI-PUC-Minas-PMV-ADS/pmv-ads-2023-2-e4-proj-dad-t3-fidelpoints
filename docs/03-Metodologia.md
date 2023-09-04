
# Metodologia

A metodologia contempla as definições das ferramentas utilizadas pela equipe tanto para a manutenção dos códigos e demais artefatos, quanto para a organização do time na execução das tarefas do projeto. 

No que diz respeito a esse segundo ponto, o grupo se organiza em reuniões semanais (sprint) que buscam discutir, organizar e estabelecer tarefas a curto prazo, bem como atualizar o estado atual do projeto em relação aos objetivos de longo prazo. Para tanto, é utilizado como recurso o Trello, que permite um registro das várias atividades e artefatos do projeto na forma de etiquetas que podem ser movidas de maneira a mostrar aquilo que já foi realizado (done) e aquilo que segue pendente e será realizado durante a semana, de acordo com o plano de sprint (to do).  

Como o Trello permite anotar a quem compete cada tarefa pendente e pode ser atualizado individualmente pelos membros designados, caberá a cada membro realizar suas tarefas (to do) e depois atualizar o Trello, marcando-as como realizadas (done). Isso contribui para manter um quadro nítido daquilo que está sendo feito e do que ainda há por fazer. Além disso, também permite registrar as ações individuais dos membros do grupo. Assim, as atividades que estão no backlog .

## Relação de Ambientes de Trabalho

Os artefatos do projeto são desenvolvidos a partir de diversas plataformas e a relação dos ambientes com seu respectivo propósito deverá ser apresentada em uma tabela que especifica que detalha Ambiente, Plataforma e Link de Acesso. 
Nota: Vide documento modelo do estudo de caso "Portal de Notícias" e defina também os ambientes e frameworks que serão utilizados no desenvolvimento de aplicações móveis.

## Controle de Versão

Os artefatos do projeto são desenvolvidos a partir de diversas plataformas e a relação dos ambientes com seu respectivo propósito é apresentada na tabela a seguir.  

|Ambiente| Plataforma|Link de Acesso |
|---------| ----------- |  --------------|
|Repositório de código fonte|Git Hub| [https://github.com/ICEI-PUC-Minas-PMV-ADS/pmv-ads-2023-2-e4-proj-dad-t3-fidelpoints/  |
Documentos do Projeto|Pasta Src do projeto|[https://github.com/ICEI-PUC-Minas-PMV-ADS/pmv-ads-2023-2-e4-proj-dad-t3-fidelpoints/tree/main/src | 
|Projeto de Interface e Wireframes| Figma|  [https://www.figma.com/proto/aXiLKAsuXGIsSiGctHkWxe/Tela-de-Cadastro?type=design&node-id=2-2&t=hHIRKqPifNLOT7pc-1&scaling=min-zoom&page-id=0%3A1&starting-point-node-id=2%3A2&show-proto-sidebar=1&mode=design | 
|Gerenciamento do Projeto| Trello|   [https://trello.com/b/ReQ9CSUv/fidelpoints  |

O projeto segue a seguinte convenção para o nome de branches:

- `main`: versão estável já testada do software
- `unstable`: versão já testada do software, porém instável
- `testing`: versão em testes do software
- `dev`: versão de desenvolvimento do software

Quanto à gerência de issues, o projeto adota a seguinte convenção para
etiquetas:

- `documentation`: melhorias ou acréscimos à documentação
- `bug`: uma funcionalidade encontra-se com problemas
- `enhancement`: uma funcionalidade precisa ser melhorada
- `feature`: uma nova funcionalidade precisa ser introduzida

Discuta como a configuração do projeto foi feita na ferramenta de versionamento escolhida. Exponha como a gerência de tags, merges, commits e branchs é realizada. Discuta como a gerência de issues foi realizada.

> **Links Úteis**:
> - [Microfundamento: Gerência de Configuração](https://pucminas.instructure.com/courses/87878/)
> - [Tutorial GitHub](https://guides.github.com/activities/hello-world/)
> - [Git e Github](https://www.youtube.com/playlist?list=PLHz_AreHm4dm7ZULPAmadvNhH6vk9oNZA)
>  - [Comparando fluxos de trabalho](https://www.atlassian.com/br/git/tutorials/comparing-workflows)
> - [Understanding the GitHub flow](https://guides.github.com/introduction/flow/)
> - [The gitflow workflow - in less than 5 mins](https://www.youtube.com/watch?v=1SXpE08hvGs)

## Gerenciamento de Projeto

### Divisão de Papéis

A equipe utiliza metodologias ágeis, tendo escolhido o Scrum como base para definição do processo de desenvolvimento. Ela está organizada da seguinte maneira: 

A equipe está organizada da seguinte maneira: 

### Scrum Master: 
Renato Conceição Silva 
### Product Owner: 
Stephanie Ingrid
### Gerente de projeto
Edglei Marques
### Equipe de Desenvolvimento :
Edglei Marques - Dev FrontEnd
Stephanie Ingrid - Dev BackEnd
Renato Conceição - Analista de teste

### Processo

Para organização e distribuição das tarefas do projeto, a equipe está utilizando o Trello estruturado com as seguintes listas:   

### Recursos:
essa lista mantém templates de tarefas recorrentes com as configurações padronizadas que todos deverão seguir. O objetivo é permitir a cópia desses templates para agilizar a criação de novos cartões. 

### Backlog: 
recebe as tarefas a serem trabalhadas e representa o Product Backlog. Todas as atividades identificadas no decorrer do projeto também devem ser incorporadas a essa lista. 
### To do: 
essa lista representa o Sprint Backlog. Trata-se do Sprint atual em que estamos trabalhando. 
### Doing: 
quando uma tarefa tiver sido iniciada, ela será movida para cá. 
### Test: 
trata-se da checagem de qualidade. Quando as tarefas são concluídas, eles serão movidas para o “CQ”. No final da semana, essa lista será revista para garantir que tudo saia perfeitamente. 
### Done: 
nessa lista são colocadas as tarefas que passaram pelos testes de controle de qualidade e que estão prontas para serem entregues ao usuário. Não há mais edições ou revisões a serem feitas aqui, sendo que as tarefas podem ser agendadas por estarem prontas para a ação.


O quadro kanban do grupo no Trello — disponível através da URL <a href=https://trello.com/b/Y0TsILAi/projeto-academia> LINK DO QUADRO </a> — é apresentado, no estado atual, na Figura abaixo. A definição dessa estrutura se baseou na proposta feita por Littlefield (2016).

![Processo 1](img/Trelo.JPG)

A tarefas são, ainda, etiquetadas em função da natureza da atividade e seguem o seguinte esquema de cores/categorias: 

- Documentação;
- Desenvolvimento;
- Infraestrutura;
- Testes;
- Gerência de Projetos.

### Ferramentas

As ferramentas empregadas no projeto são:

- VsCode;
- Figma;
- Trello;
- Spring boot;
- React.

O editor de código foi escolhido porque ele possui uma integração com o sistema de versão. As ferramentas de comunicação utilizadas possuem integração semelhante e por isso foram selecionadas. Por fim, para criar diagramas utilizamos essa ferramenta por melhor captar as necessidades da nossa solução.
