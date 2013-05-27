Hippo job cleaner
=================

This JCR runner cleans up scheduled (de)publication jobs. Useful in case upgraded repositories throw deserialization
errors when running the CMS. 

Please note that this runner DOES NOT BACKUP the jobs, but simply flushes them. To get a list of current scheduled jobs,
please use the repository console and execute appropriate queries.

Local development
build & run
> mvn clean compile exec:java

rerun (faster)
> mvn -o -q compile exec:java

Build distributable application
build
> mvn clean package appassembler:assemble

- copy runner.properties to target/hippo-job-cleaner-${version}/bin
- change/check username and password in runner.properties
- compress the assembled directory and distribute

run app
cd bin
chmod +x jcr-runner
./jcr-runner
