# Requerimentos para executar

- SQL Server
  - Caso tenha docker instalado, pode ser usado o seguinte o comando para baixar SQL Server `docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=yourStrong(!)Password" -p 1433:1433 -d mcr.microsoft.com/mssql/server`
  - Caso não tenha docker, pode ser usado o [SQL Express](https://go.microsoft.com/fwlink/p/?linkid=866658)
    - Usando essa instalação é necessário alterar a linha 9 do arquivo `API/appsettings.json` para `"DefaultConnection": "Server=localhost;Database=textbook;Trusted_Connection=True;"`

- .NET 6 - [Link Download](https://dotnet.microsoft.com/en-us/download/dotnet/thank-you/sdk-6.0.300-windows-x64-installer)

- Não é necessário mas recomendável ter o [Visual Studio 2022](https://visualstudio.microsoft.com/thank-you-downloading-visual-studio/?sku=Community&channel=Release&version=VS2022&source=VSLandingPage&cid=2030&passive=false) 

# Para Executar o Projeto

### Visual Studio

Caso possua o visual studio, basta abrir o arquivo `API.sln` e clicar em executar no topo

### Com a linha de comando

Abrir um cmd e caminha até a pasta `API` e executar o comando `dotnet watch`. 

---

- Uma documentação da API é gerada automáticamente em `https://localhost:7155/swagger/index.html`

- Para acessar uma rota, basta fazer uma requisição a `https://localhost:7155/{dadodesejado}`. Exemplo: um get a `https://localhost:7155/chapter/1` retornará todos os capítulos que pertencem ao livro de id 1.

- Para alterar o schema do banco, basta alterar alguma classe no projeto `Domain/Entities` e descomentar a linha 37 do arquivo `Infra.Database/InfraModuleConfiguration.cs` . O banco será recriado com o novo modelo.
- É usado o entity framework para acessar o banco. Exemplos de queries podem ser encontrados em `Infra.Database/Repositories`
- Os endpoints Estão em `API/Controllers`.