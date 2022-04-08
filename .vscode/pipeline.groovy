pipeline {
    agent any

    triggers {
        cron('* * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/shantdashjian/spring-petclinic.git'
            }
        }
        stage('Build') {
            steps {
                sh "./mvnw clean package"
            }

            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
