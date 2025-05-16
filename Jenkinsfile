pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17' // Java + Maven image
            args '-v $HOME/.m2:/root/.m2' // Cache dependencies
        }
    }

    environment {
        // Set your EC2 details here
        EC2_USER = 'ec2-user'
        EC2_HOST = 'your-ec2-public-ip'
        EC2_KEY_PATH = '/var/jenkins_home/.ssh/your-key.pem' // Jenkins's .ssh directory
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/your-username/airline-management.git' // <-- Replace with your repo URL
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Copy JAR to EC2') {
            steps {
                sh """
                    chmod 400 $EC2_KEY_PATH
                    scp -o StrictHostKeyChecking=no -i $EC2_KEY_PATH target/*.jar $EC2_USER@$EC2_HOST:/home/ec2-user/app.jar
                """
            }
        }

        stage('Start App on EC2') {
            steps {
                sh """
                    ssh -o StrictHostKeyChecking=no -i $EC2_KEY_PATH $EC2_USER@$EC2_HOST '
                        pkill -f "java -jar" || true
                        nohup java -jar /home/ec2-user/app.jar > output.log 2>&1 &
                    '
                """
            }
        }
    }

    post {
        success {
            echo '✅ Deployment successful!'
        }
        failure {
            echo '❌ Build or deployment failed.'
        }
    }
}
