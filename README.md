# Ecossistema de Streaming - Solução das Questões 1 e 2

## Visão Geral

Nesta etapa do projeto, o framework de streaming foi expandido para conectar o domínio técnico de mídia com o domínio comercial da plataforma.

A solução foi construída aplicando padrões de projeto para manter baixo acoplamento, respeitar os princípios SOLID e permitir evolução do sistema sem modificar classes existentes a cada nova funcionalidade.

Os padrões utilizados foram:

* **Composite**: representação de pacotes comerciais compostos por outros conteúdos.
* **Builder**: criação simplificada de pacotes complexos sem construtores extensos.
* **Visitor**: implementação de novas operações sobre os conteúdos sem alterar as classes do domínio.

---

# Questão I - Pacotes Comerciais

## Problema

A operadora precisava criar promoções complexas como:

* Trilogia Matrix;
* Coleções de filmes;
* Pacotes contendo outros pacotes;
* Séries;
* Filmes individuais.

A implementação inicial possuía construtores grandes e difíceis de manter.

Exemplo do problema:

```java
new Pacote(
    "Coleção Sci-Fi",
    new Pacote(...),
    new Pacote(...),
    new Filme(...),
    new Serie(...)
);
```

Além disso, um pacote precisava ser tratado da mesma forma que um filme, permitindo calcular:

* preço total;
* duração total.

---

# Solução da Questão I

Foram aplicados os padrões:

## Composite

Permitiu representar uma árvore de conteúdos.

Agora um pacote pode conter:

* Filmes;
* Séries;
* Episódios;
* Outros pacotes.

A estrutura passou a tratar componentes simples e compostos da mesma maneira.

---

## Builder

Responsável por criar pacotes de forma legível.

Exemplo:

```java
Pacote pacote = new PacoteBuilder("Coleção Sci-Fi")
        .addItem(trilogiaMatrix)
        .addItem(starWars)
        .addItem(blackMirror)
        .build();
```

Isso elimina construtores gigantes e facilita a criação de novas promoções.

---

# Classes alteradas - Questão I

## `Pacote`

Caminho:

```
src/br/edu/ifba/inf011/model/comercial/Pacote.java
```

Alterações:

Antes:

```java
List<Filme> filmes;
```

Depois:

```java
List<PlaylistItem> itens;
```

Agora o pacote aceita qualquer componente comercial.

Responsabilidades adicionadas:

* armazenar itens filhos;
* adicionar novos conteúdos;
* calcular preço recursivamente;
* calcular duração recursivamente;
* aceitar Visitor.

Métodos principais:

```java
addItem()
getItens()
getPreco()
getDuracao()
accept()
```

---

## `Filme`

Caminho:

```
src/br/edu/ifba/inf011/model/comercial/Filme.java
```

Alterações:

Passou a implementar:

```java
ProdutoComercial
```

Mantém:

* título;
* preço;
* timeline.

Agora também participa da estrutura Composite.

---

## `Serie`

Caminho:

```
src/br/edu/ifba/inf011/model/comercial/Serie.java
```

Alterações:

Passou a participar da hierarquia comercial.

Permite:

* agrupamento de episódios;
* cálculo de duração;
* cálculo de preço;
* exportação via Visitor.

---

## `Episodio`

Caminho:

```
src/br/edu/ifba/inf011/model/comercial/Episodio.java
```

Alterações:

Passou a aceitar Visitor.

---

# Classes criadas - Questão I

## `ProdutoComercial`

Caminho:

```
src/br/edu/ifba/inf011/model/comercial/ProdutoComercial.java
```

Interface criada para separar:

* itens comerciais;
* itens externos da playlist.

Define:

```java
Double getPreco();

Double getDuracao();
```

Exemplos de produtos comerciais:

* Filme;
* Série;
* Pacote.

---

## `PacoteBuilder`

Caminho:

```
src/br/edu/ifba/inf011/model/comercial/builder/PacoteBuilder.java
```

Responsável por construir pacotes complexos.

Métodos:

```java
addItem()

build()
```

---

---

# Questão II - Operações Analíticas

## Problema

A equipe de dados precisava constantemente adicionar novas operações:

* exportação XML;
* cálculo de largura de banda;
* relatórios;
* extração de informações.

A solução antiga obrigava alterar:

* Filme;
* Episodio;
* MP3;
* Video;
* Pacote.

Isso violava o princípio:

## Open/Closed Principle (OCP)

Classes deveriam estar abertas para extensão e fechadas para modificação.

---

# Solução da Questão II

Foi aplicado o padrão:

# Visitor

As classes de domínio não conhecem mais operações externas.

Elas apenas aceitam visitantes.

Antes:

```java
filme.toXML();
filme.getBandwidth();
```

Depois:

```java
filme.accept(visitor);
```

Novas funcionalidades são adicionadas criando novos Visitors.

---

# Classes alteradas - Questão II

## `PlaylistItem`

Caminho:

```
src/br/edu/ifba/inf011/model/playlist/PlaylistItem.java
```

Antes:

Possuía operações:

```java
toXML()

getBandwidth()
```

Depois:

Responsável apenas por aceitar visitantes:

```java
void accept(PlaylistVisitor visitor);
```

---

## `Playlist`

Caminho:

```
src/br/edu/ifba/inf011/model/playlist/Playlist.java
```

Alterações:

Passou a delegar operações para Visitors.

Métodos:

```java
toXML()

getBandaTotal()
```

Agora funcionam como uma fachada para execução dos visitantes.

---

## `MP3`

Caminho:

```
src/br/edu/ifba/inf011/model/playlist/MP3.java
```

Alterações:

Adicionado:

```java
accept(PlaylistVisitor visitor)
```

Não possui mais lógica de exportação.

---

## `Video`

Caminho:

```
src/br/edu/ifba/inf011/model/playlist/Video.java
```

Alterações:

Adicionado suporte ao Visitor.

---

# Classes criadas - Questão II

## `PlaylistVisitor`

Caminho:

```
src/br/edu/ifba/inf011/model/playlist/PlaylistVisitor.java
```

Interface base dos visitantes.

Define:

```java
visit(Filme)

visit(Serie)

visit(Episodio)

visit(Pacote)

visit(MP3)

visit(Video)
```

---

## `XMLVisitor`

Caminho:

```
src/br/edu/ifba/inf011/avaliacao3/Questao2/XMLVisitor.java
```

Responsabilidade:

Gerar XML de qualquer conteúdo da playlist.

Exemplo:

```xml
<playlist>

    <pacote titulo="Trilogia Matrix">

        <filme titulo="Matrix"/>

        <filme titulo="Matrix Reloaded"/>

    </pacote>

</playlist>
```

---

## `BandwidthVisitor`

Caminho:

```
src/br/edu/ifba/inf011/avaliacao3/Questao2/BandwidthVisitor.java
```

Responsabilidade:

Calcular consumo total de banda.

Ele percorre:

* filmes;
* séries;
* pacotes;
* MP3;
* vídeos.

Sem alterar nenhuma classe do domínio.

---

# Resultado Final

## Questão I

Resolvida utilizando:

* Composite;
* Builder.

Benefícios:

* Pacotes podem conter outros pacotes.
* Filmes e pacotes possuem tratamento uniforme.
* Criação de promoções ficou simples.
* Cálculos de preço e duração são recursivos.

---

## Questão II

Resolvida utilizando:

* Visitor.

Benefícios:

* XML não pertence mais às classes de domínio.
* Novas análises podem ser adicionadas sem alterar entidades existentes.
* Código respeita SOLID.
* Redução de acoplamento.

---

# Fluxo final da arquitetura

```
                 ProdutoComercial
                       |
        --------------------------------
        |              |               |
      Filme          Serie          Pacote
                                      |
                                  vários itens


Playlist
   |
   |
accept(visitor)
   |
   |
-----------------------------
|                           |
XMLVisitor          BandwidthVisitor

```

A arquitetura final permite que a plataforma continue evoluindo sem modificar o núcleo do domínio comercial ou técnico.
