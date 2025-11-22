helm uninstall elasticsearch -n logging

kubectl delete pvc -n logging --all

kubectl get all,pvc -n logging