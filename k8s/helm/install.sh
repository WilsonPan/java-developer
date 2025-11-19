kubectl create namespace mysql

kubectl apply -f mysql-local-pvc.yaml

sleep 5

helm install my-mysql bitnami/mysql -f mysql-values.yaml -n mysql --create-namespace --version=9.14.0