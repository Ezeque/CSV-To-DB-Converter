


# CSV to Database


Este README fornece instruções sobre como construir e executar um contêiner Docker usando o Dockerfile fornecido.

### Pré-requisitos:

-   Docker instalado em sua máquina.

### Passo 1: Configurações do Banco de Dados:

É preciso alterar o Dockerfile do projeto com suas informações de banco de dados, as variáveis de ambiente definidas no arquivo são:

1. DATABASE_DRIVER: Refere-se ao driver do seu banco de dados.
2. DATABASE_URL: A url por onde se é possível acessar seu banco de dados.
3. DATABASE_PW: A senha do seu usuário do banco de dados.
5. DATABASE_USERNAME: O nome do seu usuário do banco de dados.
6. FILES_PATH: O diretório onde serão armazenado os arquivos temporários do projeto.

### Passo 1: Construindo a Imagem

1.  Navegue até o diretório onde o Dockerfile está localizado.
2.  Abra o terminal no diretório escolhido.
3.  Execute o seguinte comando para construir a imagem:

        docker build -t nome_da_sua_imagem:tag .


Substitua `nome_da_sua_imagem` pelo nome desejado para a imagem e `tag` pela versão ou tag desejada.

### Passo 2: Executando o Contêiner

1.  Após a construção bem-sucedida da imagem, execute o seguinte comando para iniciar o contêiner:

bashCopy code

    `docker run -p 8080:8080 -d nome_da_sua_imagem:tag` 

Este comando inicia o contêiner em segundo plano (`-d`) e mapeia a porta 8080 do contêiner para a porta 8080 do host (`-p 8080:8080`).

### Passo 3: Verificando a Execução

1.  Realize agora um requisição para a porta 8080 no endpoint `/batch/start`
2. O corpo da requisição deve conter um arquivo csv com cada entrada no seguinte formato:

        nome, cargo, departamento, salário

4.  Certifique-se de que os dados foram salvos corretamente.