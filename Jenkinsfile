pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven'
    }

    stages {

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run App') {
            steps {
                bat '''
                netstat -ano | findstr :8080 > nul
                if %ERRORLEVEL%==0 (
                    for /F "tokens=5" %%a in ('netstat -ano ^| findstr :8080') do taskkill /F /PID %%a
                )
                '''
                bat 'start java -jar target\\*.jar'
            }
        }
    }
}
