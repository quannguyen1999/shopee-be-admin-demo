# Shoppe-be-admin-demo
# Run docker
# Step 0 run file jar
docker run --name postgres -p 5431:5432 POSTGRES_PASSWORD=postgres -d postgres
# Then
mvn install -DskipTests -Dmaven.test.skip=true

# Step 1
run shopee-be-account-demo

# Step 2 build
docker build . -t quannguyen1999/shopee-be-admin-demo
# or
mvn spring-boot:build-image (reject - buildpack to slow - #TODO had bug)
# or
mvn compile jib:dockerBuild (use this - google job fastest - #TODO had bug)

# Step 3 run
docker run -d -p 8080:8080 quannguyen1999/shopee-be-admin-demo

Access: http://localhost:8080/swagger-ui/index.html#/ (get full list api)

