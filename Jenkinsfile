pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven'
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Stop Existing App') {
            steps {
                bat '''
                netstat -ano | findstr :8080 > nul
                if %ERRORLEVEL%==0 (
                    echo Port 8080 is in use. Killing process...
                    for /F "tokens=5" %%a in ('netstat -ano ^| findstr :8080') do taskkill /F /PID %%a
                ) else (
                    echo Port 8080 is free.
                )
                '''
            }
        }

        stage('Run App') {
            steps {
                bat '''
                echo Starting Spring Boot app...
                start "" java -jar target\\demo-0.0.1-SNAPSHOT.jar
                '''
            }
        }
    }

    post {
        success {
            echo 'Build & Deployment Successful ✅'
        }
        failure {
            echo 'Build Failed ❌'
        }
    }
}
