helm install prometheus prometheus-community/kube-prometheus-stack \
  --version 79.7.1 \
  -f custom-values.yaml \
  --namespace monitoring