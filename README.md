# Imdb Movies Explorer

![GitHub repo size](https://img.shields.io/github/repo-size/landowat/imdb-movies-explorer?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/landowat/imdb-movies-explorer?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/landowat/imdb-movies-explorer?style=for-the-badge)

![Óculos 3D para filmes e ingressos](https://i.imgur.com/cHOJjzq.jpg)

Imagem por [Freepik](https://br.freepik.com/fotos-gratis/oculos-3d-para-filmes-e-ingressos-acima-da-vista_29803911.htm#fromView=search&page=1&position=0&uuid=bd1af71b-e597-45bb-a3dc-754d6dbbfbc8)

> Este projeto tem como objetivo explorar a API do IMDb para listar e navegar pelos filmes cadastrados na plataforma. A aplicação faz requisições HTTP, processa as respostas em formato JSON e exibe as informações relevantes de maneira acessível. Este projeto serve como base para futuras integrações e análises de dados relacionadas ao mundo do cinema.

## 🏆 Desafio Alura: 7DaysOfCode

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas para as seguintes tarefas:

✅ #7DaysOfCode - Dia 1/7  
✅ #7DaysOfCode - Dia 2/7  
✅ #7DaysOfCode - Dia 3/7  
✅ #7DaysOfCode - Dia 4/7  
✅ #7DaysOfCode - Dia 5/7  
⏳ #7DaysOfCode - Dia 6/7  
⏳ #7DaysOfCode - Dia 7/7

## 📝 Detalhamento

O que foi implementado até o momento no projeto:

### **#7DaysOfCode - Dia 1/7**
- Criar conta no ~~IMDb~~ OMDb para ter a chave de acesso ao serviço (apiKey);
- Requisição HTTP do tipo GET utilizando o pacote **_java.net.http_** e as classes **_HttpRequest_**, **_HttpClient_** e **_HttpResponse_**;

> Obs: A API IMDb não está mais disponível, por isso foi decidido utilizar a API OMDb.

### **#7DaysOfCode - Dia 2/7**
- Extrair informação de JSON com lista de filmes;
- Para cada filme, extrair as informações dos campos title, poster, year e type;
- Utilizar métodos da classe `java.lang.String` como `substring()`, `split()` e `replace()`.

### **#7DaysOfCode - Dia 3/7**
- Reorganizar os dados em uma estrutura de objeto mais adequada (a classe `Movie`), em vez de usar listas separadas para cada atributo.

>`QUESTÃO ALURA`
>Algumas reflexões: você acha que faz sentido ter setters ou um construtor padrão? Um filme deve ser interfaceado? Deve ser imutável? Justifique a sua decisão no seu repositório.
>
>Imutabilidade: A classe `Movie` é imutável porque os atributos de um filme não devem ser alterados após a criação. Isso evita efeitos colaterais e melhora a segurança em cenários concorrentes.
>
>Sem Setters ou Construtor Padrão: Setters foram omitidos para garantir imutabilidade, e o uso de um construtor padrão (sem parâmetros) não faz sentido, já que todos os atributos de um filme devem ser definidos na criação.
>
>Não Interfaceado: A classe `Movie` não foi interfaceada porque, no escopo atual do projeto, não há necessidade de diferentes implementações de filmes. Mantemos o código simples e direto.

### **#7DaysOfCode - Dia 4/7**
- Criação de uma nova classe HTMLGenerator, para criação de página HTML onde serão apresentados os filmes selecionados.
- Criação da classe MovieUtils para incluir métodos auxiliares, como parseMovies e writeMoviesToHtml, melhorando a organização e a modularidade do código.
- Atualização da main para usar os métodos da nova classe MovieUtils, melhorando a clareza e a manutenção do código.
- Criação de diretório de saída de página HTML.

### **#7DaysOfCode - Dia 5/7**
- Encapsulamento da chamada API na classe ImdbApiClient.
- Encapsulamento do parseamento do JSON na classe ImdbMovieJsonParser.

## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você instalou a versão mais recente de `Java JDK 22.0.1`.
- Você configurou corretamente o `Apache Maven 3.9.8` para gerenciar as dependências do projeto.
- Você tem uma máquina `Windows, Linux ou Mac` (compatível com todos os sistemas operacionais).
- Você leu a documentação da API OMDb para se familiarizar com as chamadas de API e requisitos: [Documentação OMDb](https://www.omdbapi.com/).

## 🚀 Instalando Imdb Movies Explorer

Para instalar o Imdb Movies Explorer, siga estas etapas:

1. Clone o repositório:

    ```bash   
    git clone https://github.com/landowat/imdb-movies-explorer.git
    cd imdb-movies-explorer
    ```

2. Configure sua chave de API da OMDb:

    - Crie um arquivo `.env` na raiz do projeto com o seguinte conteúdo:

    ```env
    API_KEY=<sua_chave_omdb>
    MOVIE_REPORT="output/movies_report.html"
    ```

3. Compile o projeto com Maven:

    ```bash
    mvn clean install
    ```

4. Pronto! Agora o projeto está instalado e pronto para ser executado.

## ☕ Usando Imdb Movies Explorer

Para usar o Imdb Movies Explorer, siga estas etapas:

1. **Executar a aplicação:**
    - Após compilar o projeto, você pode executá-lo usando o Maven:
      ```bash
      mvn exec:java -Dexec.mainClass="com.orlandowatanabe.Main"
      ```
    - Isso iniciará a aplicação que faz a busca de filmes na API OMDb.

2. **O que a aplicação faz:**
    - A aplicação faz uma requisição para a API OMDb para buscar filmes que contêm o termo "Avengers".
    - Os dados dos filmes retornados são extraídos (título, ano, IMDb ID, tipo e URL do pôster) e armazenados como objetos na classe `Movie`.
    - ~~Os filmes são listados no console, exibindo todas as informações capturadas.~~
    - Os filmes serão apresentados em uma página HTML, gerada no diretório output.

   **Detalhes adicionais:**
    - A aplicação utiliza expressões regulares para extrair informações do JSON retornado.
    - As informações extraídas incluem título, ano, IMDb ID, tipo e URL do pôster de cada filme.
    - A lista de filmes é exibida no console após a extração.

3. **Possíveis ajustes:**
    - Você pode alterar o termo de busca ao modificar a URL na linha de código onde a variável `url` é definida:
      ```java
      String url = "http://www.omdbapi.com/?apikey=" + apiKey + "&s=TermoDeBusca";
      ```

### Notas:
- Certifique-se de que sua chave de API esteja válida e ativa.
- O aplicativo foi projetado para fazer chamadas de API e extrair dados, então é recomendável que você tenha uma conexão de internet ativa para executá-lo.

## 📫 Contribuindo para Imdb Movies Explorer

Para contribuir com Imdb Movies Explorer, siga estas etapas:

1. Bifurque este repositório.
2. Crie um branch: `git checkout -b <nome_branch>`.
3. Faça suas alterações e confirme-as: `git commit -m '<mensagem_commit>'`
4. Envie para o branch original: `git push origin <nome_branch>`
5. Crie a solicitação de pull.

Como alternativa, consulte a documentação do GitHub em [como criar uma solicitação pull](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request).

## 🤝 Colaboradores

Agradecemos às seguintes pessoas que contribuíram para este projeto:

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/landowat" title="Perfil de Orlando Watanabe Jr. no GitHub">
        <img src="https://i.imgur.com/yyUNnpp.jpeg" width="100px;" alt="Foto de Orlando Watanabe Jr."/><br>
        <sub>
          <b>Orlando Watanabe Jr.</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://www.alura.com.br/" title="Alura - Plataforma de Ensino">
        <img src="https://i.imgur.com/L6K36ZW.jpeg" width="100px;" alt="Logo da Alura"/><br>
        <sub>
          <b>Alura</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

## 😄 Seja um dos contribuidores

Quer fazer parte desse projeto? Clique [AQUI](CONTRIBUTING.md) e leia como contribuir.

## 📝 Licença

Esse projeto está sob licença. Veja o arquivo [LICENÇA](LICENSE.md) para mais detalhes.
