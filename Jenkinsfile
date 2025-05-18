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
        GIT_REPO = 'https://github.com/Ashwinib26/Airlines-Management-System.git'
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo '📥 Cloning the project from GitHub...'
                git "${GIT_REPO}"
            }
        }

        stage('Build Project') {
            steps {
                echo '🏗️ Building the project...'
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                echo '🧪 Running test cases...'
                sh 'mvn test'
            }
        }

        stage('Deploy to EC2') {
            steps {
                echo '🚀 Deploying to AWS EC2...'
                sh """
                    echo '🔐 Setting permissions for SSH key...'
                    chmod 400 $EC2_KEY_PATH

                    echo '📤 Transferring JAR file to EC2...'
                    scp -o StrictHostKeyChecking=no -i $EC2_KEY_PATH target/$JAR_NAME $EC2_USER@$EC2_HOST:$REMOTE_PATH

                    echo '🖥️ Starting app on EC2...'
                    ssh -o StrictHostKeyChecking=no -i $EC2_KEY_PATH $EC2_USER@$EC2_HOST '
                        echo "🔁 Stopping any existing Java process..."
                        pkill -f "java -jar" || true

                        echo "🚀 Starting new instance..."
                        nohup java -jar $REMOTE_PATH > app.log 2>&1 &
                    '
                """
            }
        }
    }

    post {
        success {
            echo '✅ Build, Test, and Deployment completed successfully!'
        }
        failure {
            echo '❌ Build, Test, or Deployment failed!'
        }
    }
}
