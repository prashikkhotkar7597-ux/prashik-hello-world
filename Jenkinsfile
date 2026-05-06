pipeline {
    agent any

       tools {
        jdk 'JDK-21'    
        maven 'Maven'   
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Run App') {
            steps {
                bat '''
                for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080') do taskkill /F /PID %%a
                '''
                bat '''
                start /b java -jar target\\*.jar
                '''
            }
        }
    }
}
