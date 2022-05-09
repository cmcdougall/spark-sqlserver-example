package sqlexample

import org.apache.spark.sql.SparkSession

object SQLServerExample {
  def main(args: Array[String]): Unit = {
    val database_name = "AdventureWorks2019"
    val table_name = "Person.Person"
    val username = "cameron"
    val password = ""

    val server_name =
      s"jdbc:sqlserver://eim-oltp-prototype.public.ccba9713eda4.database.windows.net:3342;user=$username@eim-oltp-prototype;password=$password;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.ccba9713eda4.database.windows.net;loginTimeout=30"
    val url = server_name + ";" + "databaseName=" + database_name + ";"

    val spark = SparkSession.builder
      .appName("Test SQL Connect")
      .getOrCreate()

    val jdbcDF = spark.read
      .format("com.microsoft.sqlserver.jdbc.spark")
      .option("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver")
      .option("url", url)
      .option("dbtable", table_name)
      .option("user", username)
      .option("password", password)
      .load()

    jdbcDF.show()

    spark.stop()
  }
}
