echo Iniciando Servidor de Descubrimiento Eureka (Puerto 8761)...
cd eureka
start cmd /k "mvnw spring-boot:run"

echo Esperando 12 segundos a que Eureka se estabilice...
timeout /t 12 /nobreak > null

echo Iniciando API Gateway...
cd ../gateway
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Articulos...
cd ../articulos
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Bodegas...
cd ../bodegas
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Clientes...
cd ../clientes
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Despachos...
cd ../despachos
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Detalle-pedidos...
cd ../detalles-pedidos
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Inventarios...
cd ../inventarios
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Pedidos...
cd ../pedidos
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Proveedores...
cd ../proveedores
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Recepciones...
cd ../recepciones
start cmd /k "mvnw spring-boot:run"

echo Iniciando Microservicio Trabajadores...
cd ../trabajadores
start cmd /k "mvnw spring-boot:run"

echo Ecosistema lanzado. Dashboard disponible en http://localhost:8761