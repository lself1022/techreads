pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                sh 'chmod +x mvnw && ./mvnw package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing...'
                sh './mvnw test'
            }
        }
    }
}