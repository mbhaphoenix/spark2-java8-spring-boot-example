# Introduction 
This module converts a partition with many files in a source table to a partition with a single file in a target table (Hive) having the same schema as the source.
 
This module uses :
* Spark 2.1.0cloudera version 
* Spring-boot 
* java8

The goal of this module is to provide an example of integration between the components above. 

I tried to put as much good practices as possible, among other things:
* Outsourcing configuration and arguments
* loose coupling
* high cohesion
* Logging and its configuration
* Unit tests
* Integration tests
* Encapsulation

 
*All proposals and reviews are welcome.*

# Build
This is a maven module which deliverable is a *tar.gz* artefact in this form 
```
partition-converter-<version>-dist.tar.gz
```
To build the artefact and run only the unit tests 
``` 
mvn clean package 
```
To build the artefact and run the unit and integration tests  
```
mvn clean verify 
```

After uncompressing the artefact : 
```
tar -xzvf partition-converter-<version>-dist.tar.gz
```

You will get a directory : *partition-converter-\<version\>* with this structure :

```
- run.sh
- partition-converter.jar
- config\
    - application.properties 
    - log4j.properties
```

Before you run the module, make sure to fill the following arguments:
* srcDbName
* srcTableName
* targetDbName
* targetTableName
* partitionDefinition
* hiveExecStagingdir(optional)

You have the possibility to do it in several ways but the two main ones:

* Through the file
**partition-converter-\<version\>-dist/config/application.properties** with valid arguments.
* Passing the arguments to the JAR in this form --argname = \ <argvalue \> 

Validators have been applied to these arguments, so be sure to fill them otherwise your application will fail.

The *application.properties* supplied with the aretfact documents the arguments to be filled.

I set up a small shell script *run.sh* showing an example of launching the module with arguments passed to the *config/application.properties*

```
partition-converter-<version>/run.sh <parincipalName> <keytab_path>
```

Finally, do not hesitate to change *partition-converter-\<version\>/config/log4j.properties* to adapt
spark driver logging to your needs.

