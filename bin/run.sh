#!/bin/bash

if [ "$#" -ne 2 ]; then
   echo "Usage:   ./run.sh <parincipalName> <keytab_path>"
   echo "Example: ./run.sh phoenix_dev00 /home/phoenix_dev00/phoenix_dev00.keytab"
   exit 1
fi

PRINCIPAL_NAME=$1
KEYTAB_PATH=$2
MAIN=org.springframework.boot.loader.PropertiesLauncher
JAR=parquet-partition-converter.jar

/usr/bin/spark2-submit --master yarn --deploy-mode cluster --name "CSV HIVE Partition to Parquet" --class $MAIN \
        --principal $PRINCIPAL_NAME@applispfref.sipfref.local --keytab $KEYTAB_PATH \
        --files config/log4j.properties,config/application.properties \
        --num-executors 1 --executor-memory 3600m  --executor-cores 4  \
        --conf spark.memory.useLegacyMode=true --conf spark.shuffle.memoryFraction=0.8 --conf spark.storage.memoryFraction=0.05 \
        --conf spark.shuffle.service.enabled=false --conf spark.dynamicAllocation.enabled=false \
        --conf spark.driver.extraJavaOptions="-Dlog4j.configuration=log4j.properties" $JAR
exit 0