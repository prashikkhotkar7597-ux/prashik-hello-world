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
                bat 'start java -jar target\\*.jar'
            }
        }
    }
}
