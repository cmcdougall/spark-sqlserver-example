# Spark SQL Server Example Project

## Helpful Sites

[Running Spark on AKS](https://docs.microsoft.com/en-us/azure/aks/spark-job)

[Another Example of Running Spark on AKS](https://tsmatz.wordpress.com/2020/12/08/apache-spark-on-azure-kubernetes-service-aks/)

[Spark SQL Server Connector](https://github.com/microsoft/sql-spark-connector)

## Dependencies

Add the following to build.sbt

```bash
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.3.0" % "provided"
libraryDependencies += "com.microsoft.azure" % "spark-mssql-connector_2.12" % "1.2.0"
libraryDependencies += "com.microsoft.sqlserver" % "mssql-jdbc" % "10.2.0.jre11"
```

## Submit Spark Job

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