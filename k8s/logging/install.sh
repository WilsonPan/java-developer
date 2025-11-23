helm install elasticsearch elastic/elasticsearch \
  --namespace logging \
  --values elasticsearch-values.yaml \
  --version 7.17.3

helm install kibana elastic/kibana \
  --namespace logging \
  --values kibana-values.yaml \
  --version 7.17.3

helm install filebeat elastic/filebeat \
  --namespace logging \
  --values filebeat-values.yaml \
  --version 7.17.3

helm install logstash elastic/logstash \
  --namespace logging \
  --values logstash-values.yaml \
  --version 7.17.3