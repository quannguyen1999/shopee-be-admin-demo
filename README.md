# shopee-be-admin-demo
Access: http://localhost:8080/swagger-ui/index.html#/ (get full list api)

# Command run Rabbit MQ
docker run --name active-mq-test --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3-management

# Command run postgres 
docker run --name postgres -p 5431:5432 POSTGRES_PASSWORD=postgres -d postgres

# Option 1
# Run docker
# Step 0 run file jar 
mvn install -DskipTests -Dmaven.test.skip=true

# Step 1 build
docker build . -t quannguyen1999/shopee-be-admin-demo
> or
> mvn spring-boot:build-image

# Step 2 run
docker run -d -p 8080:8080 quannguyen1999/shopee-be-admin-demo

