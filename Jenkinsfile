pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven'
    }

    environment {
        APP_NAME = 'jwt-0.0.1-SNAPSHOT.jar'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                credentialsId: '93889a3c-ce05-48f0-bf25-f81924810469',
                url: 'https://github.com/prashikkhotkar7597-ux/prashik-hello-world.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Stop Existing App') {
            steps {
                bat '''
                echo Checking port 8080...

                netstat -ano | findstr :8080 > nul

                if %ERRORLEVEL%==0 (
                    echo Port 8080 is in use. Killing process...

                    for /F "tokens=5" %%a in ('netstat -ano ^| findstr :8080') do (
                        taskkill /F /PID %%a
                    )
                ) else (
                    echo Port 8080 is free.
                )
                '''
            }
        }

        stage('Run Application') {
            steps {
                bat '''
                echo Starting Spring Boot Application...

                cd target

                start "" java -jar %APP_NAME%
                '''
            }
        }

        stage('Verify Application') {
            steps {
                bat '''
                timeout /t 10

                netstat -ano | findstr :8080
                '''
            }
        }
    }

    post {

        success {
            echo 'Application deployed successfully on port 8080 ✅'
        }

        failure {
            echo 'Build or deployment failed ❌'
        }
    }
}
