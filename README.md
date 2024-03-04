# shopee-be-admin-demo
Access: http://localhost:8080/swagger-ui/index.html#/ (get full list api)

# Command run Rabbit MQ
docker run --name active-mq-test --rm -it -p 15672:15672 -p 5672:5672 rabbitmq:3-management

# Option 1
# Run docker
# Step 1 build
> docker build . -t quannguyen1999/shopee-be-admin-demo
> or
> mvn spring-boot:build-image

# Step 2 run
> docker run -d -p 8080:8080 quannguyen1999/shopee-be-admin-demo

