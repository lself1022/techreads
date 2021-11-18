pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building...'
                sh './mvnw package -DskipTests'
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