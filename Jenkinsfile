pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17' // Java + Maven Docker image
            args '-v $HOME/.m2:/root/.m2' // Use Maven cache
        }
    }

    environment {
        EC2_USER = 'ec2-user'
        EC2_HOST = '3.110.136.121'
        EC2_KEY_PATH = '/var/lib/jenkins/.ssh/airlines-keypair.pem' // Adjust path for Jenkins user
        JAR_NAME = 'airlines-management-0.0.1-SNAPSHOT.jar'
        REMOTE_PATH = '/home/ec2-user/app.jar'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/YOUR_USERNAME/airline-management.git' // ✅ Replace with your real repo
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
