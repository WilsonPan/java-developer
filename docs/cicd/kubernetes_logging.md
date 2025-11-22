# Kubernetes集成ELK日志

由于我们使用Helm部署ELK，这里将分别部署Elasticsearch、Kibana和Logstash。同时，我们还需要部署Filebeat作为日志收集器

步骤：

1. 添加Elastic Helm仓库
2. 部署Elasticsearch
3. 部署Kibana
4. 部署Logstash（可选，因为Filebeat也可以直接发送到Elasticsearch，但通常用于更复杂的处理）
5. 部署Filebeat

## 前置条件

1. 一个正在运行的 Kubernetes 集群。
> 参考: [k8s入门](https://www.cnblogs.com/WilsonPan/p/19194963)

2. 已安装 `kubectl` 并配置好对集群的访问
> 参考: [macOS上优雅运行Docker容器](https://www.cnblogs.com/WilsonPan/p/19124111)

3. 已安装 `Helm。` 
> 参考: [k8s使用helm简化安装](https://www.cnblogs.com/WilsonPan/p/19208551)

## 部署ELK Stack到Kubernetes

### 1. 添加Elastic Helm仓库

```sh
# 添加Elastic官方仓库
helm repo add elastic https://helm.elastic.co
helm repo update elastic
```

### 2. 创建命名空间

```sh
kubectl create namespace logging
```

### 3. 部署Elasticsearch

#### 3.1 创建自定义values文件

`elasticsearch-values.yaml`

```yaml
# elasticsearch-values.yaml
clusterName: "elasticsearch"
nodeGroup: "master"

replicas: 2

# 资源配置
resources:
  requests:
    cpu: "1000m"
    memory: "2Gi"
  limits:
    cpu: "2000m"
    memory: "4Gi"

# 持久化配置
volumeClaimTemplate:
  accessModes: [ "ReadWriteOnce" ]
  storageClassName: "local-path"  # 根据你的存储类修改
  resources:
    requests:
      storage: 50Gi

# 配置参数
esConfig:
  elasticsearch.yml: |
    xpack.security.enabled: true
    xpack.monitoring.collection.enabled: true

# 环境变量
extraEnvs:
  - name: "ES_JAVA_OPTS"
    value: "-Xms2g -Xmx2g"

# 启用服务
service:
  type: ClusterIP
  loadBalancerIP: ""

# 安全配置
secret:
  enabled: true
  passwords:
    elastic: "admin123!"  # 修改为强密码

# 探针配置
readinessProbe:
  failureThreshold: 3
  initialDelaySeconds: 10
  periodSeconds: 10
  successThreshold: 3
  timeoutSeconds: 5

livenessProbe:
  failureThreshold: 3
  initialDelaySeconds: 10
  periodSeconds: 10
  successThreshold: 3
  timeoutSeconds: 5
```

#### 3.2 部署Elasticsearch

```sh
helm install elasticsearch elastic/elasticsearch \
  --namespace logging \
  --values elasticsearch-values.yaml \
  --version 8.5.1
```

#### 3.3 验证Elasticsearch部署

```sh
# 检查Pod状态
kubectl get pods -n logging -l app=elasticsearch-master

# 检查服务
kubectl get svc -n logging
```
