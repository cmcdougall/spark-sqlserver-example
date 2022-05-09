https://docs.microsoft.com/en-us/azure/aks/spark-job
https://tsmatz.wordpress.com/2020/12/08/apache-spark-on-azure-kubernetes-service-aks/
https://github.com/microsoft/sql-spark-connector

Example command for a spark submit job:

```bash
./bin/spark-submit \
    --master k8s://http://127.0.0.1:8080 \
    --deploy-mode cluster \
    --name sqlserverexample \
    --class sqlexample.SQLServerExample \
    --conf spark.executor.instances=3 \
    --conf spark.kubernetes.authenticate.driver.serviceAccountName=spark \
    --conf spark.kubernetes.container.image=cjm1909jf.azurecr.io/spark:v1 \
    --conf spark.hadoop.fs.azure.account.key.sparkdemo45jh.blob.core.windows.net=<storage account key> \
    --packages org.apache.hadoop:hadoop-azure:3.2.0,com.microsoft.azure:azure-storage:8.6.3 \
    --conf spark.driver.extraJavaOptions="-Divy.cache.dir=/tmp -Divy.home=/tmp" \
    --conf spark.kubernetes.file.upload.path=wasbs://testdata@sparkdemo45jh.blob.core.windows.net/ file:///home/cmcdougall/SparkPi-assembly-0.1.0-SNAPSHOT.jar
```