Na raiz do projeto deve constar um arquivo chamado Procfile (sem extensao)

Seu conteúdo deve conter apenas a seguinte linha (projeto já conta um modelo na pasta):
web: java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war

NetBeans - Incluir no POM - NESTE EXATO 

<project ...>
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                    ...
                    </execution>
                    <execution>
                        <id>web-runner</id>
                        <phase>package</phase>
                        <goals>
                            <goal>copy</goal>
                        </goals>
                        <configuration>
                            <artifactItems>
                                <artifactItem>
                                    <groupId>com.github.jsimone</groupId>
                                    <artifactId>webapp-runner</artifactId>
                                    <version>8.5.11.3</version>
                                    <destFileName>webapp-runner.jar</destFileName>
                                </artifactItem>
                            </artifactItems>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>


Após, limpar e construir

No cliente heroku executar o seguinte comando para definir a variável de ambiente da base: 
heroku run echo \$JDBC_DATABASE_URL NOME_DA_BASE

Para executar o Deploy:
com um Bash (sim um bash por que o cmd dá erro), executar a seguinte sequencia de comandos para envio das alterações:
heroku git:remote -a NOME_DO_APP
git add .
git commit -m 'Mensagem do commit'
git push heroku -u master
heroku ps:scale web=1
