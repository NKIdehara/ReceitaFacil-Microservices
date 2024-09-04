@echo off
cls

npm run build --prefix .\front-end\ & ^
mvn clean package -DskipTests -f .\microservices\server\ & ^
mvn clean package -DskipTests -f .\microservices\gateway\ & ^
mvn clean package -DskipTests -f .\microservices\medida\ & ^
mvn clean package -DskipTests -f .\microservices\item\ & ^
mvn clean package -DskipTests -f .\microservices\ingrediente\ & ^
mvn clean package -DskipTests -f .\microservices\receita\ & ^
mvn clean package -DskipTests -f .\microservices\usuario\  & ^
mvn clean package -DskipTests -f .\microservices\publicacao\ & ^

echo docker build -t server .\microservices\server\
echo docker build -t gateway .\microservices\gateway\
echo docker build -t medida .\microservices\medida\
echo docker build -t item .\microservices\item\
echo docker build -t ingrediente .\microservices\ingrediente\
echo docker build -t receita .\microservices\receita\
echo docker build -t usuario .\microservices\usuario\
echo docker build -t publicacao .\microservices\publicacao\

docker-compose up --build & ^

echo "--- ReceitaFacil ---"