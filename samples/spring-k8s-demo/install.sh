# 切换指定JDK版本（可选）
export JAVA_HOME=`/usr/libexec/java_home -v 21`

# 可替换mvn
mvnd clean package -DskipTests

# 构建镜像
docker build -t spring-k8s-demo:latest .

sleep 3

kubectl delete -f k8s-deployment.yaml

sleep 3

kubectl apply -f k8s-deployment.yaml

kubectl get pods -w