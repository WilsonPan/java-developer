# Kubernetes使用Prometheus Stack监控系统

## 安装 kube-prometheus-stack

```sh
# 添加 Prometheus Community Helm 仓库
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo update

# 创建命名空间
kubectl create namespace monitoring

# 安装 kube-prometheus-stack
helm install prometheus-stack prometheus-community/kube-prometheus-stack \
  --namespace monitoring \
  --set prometheus.prometheusSpec.retention="7d" \
  --set grafana.adminPassword="admin" \
  --set alertmanager.alertmanagerSpec.retention="120h"
```

## 验证安装

```sh
# 检查所有 Pod 是否正常运行
kubectl get pods -n monitoring
```