pipeline {

agent any

stages {

    stage('Deploy To Ubuntu Server') {

        steps {

            sshPublisher(
                publishers: [
                    sshPublisherDesc(
                        configName: 'ubuntu-server',

                        transfers: [
                            sshTransfer(
                                execCommand: '''

                                set +e

                                export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
                                export PATH=$JAVA_HOME/bin:/usr/share/maven/bin:$PATH

                                cd /opt/prashik-hello-world || exit 1

                                echo "===== GIT PULL ====="
                                git pull origin main

                                echo "===== STOP OLD APP ====="
                                ps -ef | grep demo-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}' | xargs -r kill -9

                                echo "===== BUILD STARTED ====="
                                mvn clean package -DskipTests

                                echo "===== BUILD FINISHED ====="

                                ls -la target

                                echo "===== STARTING APPLICATION ====="

                                nohup java -jar target/demo-0.0.1-SNAPSHOT.jar --server.port=8085 > app.log 2>&1 &

                                sleep 15

                                echo "===== APPLICATION LOG ====="

                                cat app.log

                                echo "===== RUNNING PORTS ====="

                                netstat -tulnp | grep 8085 || true

                                exit 0

                                '''
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
