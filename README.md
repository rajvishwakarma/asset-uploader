# Asset Uploader!

This document is entitled to list out the detailed aspects of Asset Uploader Spring boot Application. The application would deal features to upload file and maintain status.


# Prequisite

Some configurations are required in your AWS account for this sample to work. Basically, an S3 bucket (by default asset-uplader is used, but it can be changed using amazonProperties.bucketName property), and an RDS MySQL instance open to the world. Additionally, we need an IAM user with access key and programmatic access to AWS API so that we can access AWS resources from our development machine.

## Create an IAM User

Enable programmatic access
Generate an access key for the user
Give the user the following permissions: ** AmazonS3FullAccess ** AmazonRDSFullAccess

## Create an RDS Instance

WARNING: These instructions allow you to run and test the application from within your development environment (i.e., without deploying it to AWS) using an RDS instance open to the world, which is something you should avoid in production.
Create a Mysql Instance on dev env. Please read the setup instruction to set Mysql.

DB Configuration to be managed in the application.properties file

spring.datasource.url = jdbc:mysql://localhost:3306/assets-uploader
spring.datasource.username = root
spring.datasource.password = 


## Create an S3 bucket

Finally, create an S3 bucket, name it bucket-name and give read permissions to anonymous users. Just copy and paste this aws policy to enable anonymous read access:

```mermaid
{
  "Version":"2012-10-17",
  "Statement":[
    {
      "Sid":"AddPerm",
      "Effect":"Allow",
      "Principal": "*",
      "Action":["s3:GetObject"],
      "Resource":["arn:aws:s3:::bucket-name/*"]
    }
  ]
}
```

## AWS configuration

AWS configuration that can be modified in application.properties file

```mermaid
  amazonProperties.endpointUrl = ########
  amazonProperties.accessKey = ############
  amazonProperties.secretKey = ##############
  amazonProperties.bucketName = bucket-name
```
  


# Run the application

For the impatient...

```mermaid
  git clone https://github.com/rajvishwakarma/asset-uploader.git
  cd asset-uploader
  mvn clean compile 
  mvn package
  mvn spring-boot:run
```
