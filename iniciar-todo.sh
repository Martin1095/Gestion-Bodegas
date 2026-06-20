#!/bin/bash

echo "Iniciando Servidor de Descubrimiento Eureka (Puerto 8761)..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/eureka\" && ./mvnw spring-boot:run"'

echo "Esperando 12 segundos a que Eureka se estabilice..."
sleep 12

echo "Iniciando API Gateway..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/gateway\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Articulos..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/articulos\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Bodegas..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/bodegas\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Clientes..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/clientes\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Despachos..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/despachos\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Detalle_pedido..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/detalle_pedidos\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Inventarios..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/inventarios\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Pedidos..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/pedidos\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Proveedores..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/proveedores\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Recepciones..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/recepciones\" && ./mvnw spring-boot:run"'

echo "Iniciando Microservicio Trabajadores..."
osascript -e 'tell application "Terminal" to do script "cd \"'"$(pwd)"'/trabajadores\" && ./mvnw spring-boot:run"'

echo "Ecosistema lanzado. Dashboard disponible en http://localhost:8761"