pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven'
    }

    environment {
        APP_JAR = 'demo-0.0.1-SNAPSHOT.jar'
        APP_PORT = '8085'
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
                echo Stopping old app on port %APP_PORT%...

                for /f "tokens=5" %%a in ('netstat -ano ^| findstr :%APP_PORT%') do (
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

                start cmd /c "java -jar %APP_JAR% --server.port=%APP_PORT% > app.log 2>&1"

                exit 0
                '''
            }
        }

        stage('Wait For Startup') {
            steps {
                bat '''
                echo Waiting for startup...

                ping 127.0.0.1 -n 20 > nul

                exit 0
                '''
            }
        }

        stage('Verify Application') {
            steps {
                bat '''
                echo Checking application on port %APP_PORT%...

                netstat -ano | findstr :%APP_PORT%

                echo.
                echo Calling API...
                curl http://localhost:%APP_PORT%/hello

                echo.
                echo ===== APPLICATION LOG =====
                type target\\app.log

                exit 0
                '''
            }
        }
    }

    post {

        success {
            echo 'Application deployed successfully ✅'
            echo 'URL: http://localhost:8085/hello'
        }

        failure {
            echo 'Deployment failed ❌'
        }
    }
}
