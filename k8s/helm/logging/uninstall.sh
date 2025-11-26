# 删除所有ELK组件
helm uninstall filebeat -n logging
helm uninstall logstash -n logging
helm uninstall kibana -n logging
helm uninstall elasticsearch -n logging

# 清理持久卷（如果不再需要）
kubectl delete pvc -n logging --all