# 🚀 Jenkins CI/CD Pipeline for Spring Boot Application Deployment on AWS EC2

## 📌 Project Objective

This project demonstrates how to design a Jenkins pipeline that automates the continuous integration and continuous deployment (CI/CD) of a Spring Boot application using Docker and AWS EC2.

---

## ✅ Features Implemented

1. **Checkout** the Spring Boot project created in Assignment 1 from GitHub.
2. **Use Docker** to initialize a container with Maven and JDK.
3. **Build** the Spring Boot project using Maven inside the Docker container.
4. **Run Unit Tests** to validate the codebase.
5. **Deploy the built `.jar`** file to a remote **AWS EC2** instance.
6. **Start the Spring Boot application** on the EC2 instance.

---

## 🧰 Tools & Technologies Used

- **Jenkins** – Automation server for pipeline execution.
- **Docker** – Container environment with Java and Maven.
- **Maven** – Build and dependency management.
- **Spring Boot** – Java web application framework.
- **Git** – Version control system.
- **AWS EC2** – Virtual server to deploy the application.
- **SSH & SCP** – For remote communication and file transfer.

---

## 📁 Jenkinsfile (Pipeline Script)

```groovy
pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17'
            args '-v $HOME/.m2:/root/.m2'
        }
    }

    environment {
        EC2_USER = 'ec2-user'
        EC2_HOST = '3.110.136.121'
        EC2_KEY_PATH = '/var/lib/jenkins/.ssh/airlines-keypair.pem'
        JAR_NAME = 'airlines-management-0.0.1-SNAPSHOT.jar'
        REMOTE_PATH = '/home/ec2-user/app.jar'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/Ashwinib26/Airlines-Management-System.git'
            }
        }

        stage('Build Project') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy to EC2') {
            steps {
                sh """
                    chmod 400 $EC2_KEY_PATH
                    scp -o StrictHostKeyChecking=no -i $EC2_KEY_PATH target/$JAR_NAME $EC2_USER@$EC2_HOST:$REMOTE_PATH
                    ssh -o StrictHostKeyChecking=no -i $EC2_KEY_PATH $EC2_USER@$EC2_HOST '
                        pkill -f "java -jar" || true
                        nohup java -jar $REMOTE_PATH > app.log 2>&1 &
                    '
                """
            }
        }
    }

    post {
        success {
            echo '✅ Build and Deployment succeeded!'
        }
        failure {
            echo '❌ Build or Deployment failed!'
        }
    }
}
````

---

## 🌐 Prerequisites

Make sure the following are in place before running the pipeline:

* Jenkins is installed and running
* Docker is installed on the Jenkins machine
* Your GitHub Spring Boot repo is accessible publicly or with credentials
* Your EC2 instance has:

  * Java installed
  * Port 8080 open in the security group
  * SSH access using the provided `.pem` key
* The `.pem` key is placed in Jenkins under `/var/lib/jenkins/.ssh/`

---

## 📂 Expected Project Structure

```
Airlines-Management-System/
├── src/
│   └── main/java/... (Spring Boot code)
├── pom.xml
└── Jenkinsfile
```

---

## 🧪 Output

After successful execution, your application will be:

* Built inside a Docker container
* Tested using Maven test cases
* Deployed as a `.jar` file on the AWS EC2 instance
* Running as a background process on EC2 (`java -jar app.jar`)

You can then access it using:
`http://<EC2-Public-IP>:8080`

---

## 📝 Notes

* Customize `JAR_NAME`, `REMOTE_PATH`, and `EC2_HOST` as per your setup.
* Add credentials and permissions properly in Jenkins for security.
* Use `nohup` to run the application as a background service on EC2.

---

## Thank You!!
