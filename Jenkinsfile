```groovy
pipeline {

    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven'
    }

    stages {

        stage('Deploy To Ubuntu Server') {

            steps {

                sshPublisher(
                    publishers: [
                        sshPublisherDesc(
                            configName: 'ubuntu-server',

                            transfers: [
                                sshTransfer(
                                    execCommand: """

                                    cd /opt/prashik-hello-world

                                    git pull origin main

                                    pkill -f demo-0.0.1-SNAPSHOT.jar || true

                                    mvn clean package -DskipTests

                                    nohup java -jar target/demo-0.0.1-SNAPSHOT.jar --server.port=8085 > app.log 2>&1 &

                                    sleep 15

                                    cat app.log

                                    """
                                )
                            ],

                            verbose: true
                        )
                    ]
                )

            }

        }

    }

    post {

        success {
            echo 'Deployment Successful ✅'
            echo 'Application URL: http://192.168.0.223:8085/hello'
        }

        failure {
            echo 'Deployment Failed ❌'
        }

    }

}
```
