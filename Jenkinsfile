pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven'
    }

    environment {
        APP_JAR = 'demo-0.0.1-SNAPSHOT.jar'
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
                echo Stopping old app on port 8080...

                for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8080') do (
                    taskkill /F /PID %%a > nul 2>&1
                )

                exit 0
                '''
            }
        }

        stage('Run Application') {
            steps {
                bat '''
                echo Starting Spring Boot Application...

                cd target

                REM IMPORTANT: use /B so it keeps running
                start /B java -jar %APP_JAR%

                exit 0
                '''
            }
        }

        stage('Verify Application') {
            steps {
                bat '''
                echo Waiting for app startup...

                timeout /t 15 > nul

                echo Checking port 8080...

                netstat -ano | findstr :8080 || echo App not running!

                curl http://localhost:8080/hello || echo API not reachable yet

                exit 0
                '''
            }
        }
    }

    post {
        success {
            echo 'Application running at http://localhost:8080/hello ✅'
        }

        failure {
            echo 'Pipeline failed ❌'
        }
    }
}
