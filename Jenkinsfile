pipeline {
    agent any
    stages {
        stage("build code") {
                steps {
                    bat "mvn clean install"
                }
        }

        stage("Test code") {
                steps {
                    bat "mvn test"
                }
        }

         stage("Build Image") {
                        steps {
                            bat "docker build -t jpelegrino/cenodr ."
                        }
                }

         stage("Push Image") {
                 steps {
                    script {
                        withCredentials([string(credentialsId: 'noahpwd', variable: 'dockerhubpwd')]) {
                            bat "docker login -u jpelegrino -p ${dockerhubpwd}"
                            bat "docker push jpelegrino/cenodr"
                        }
                    }
                 }
         }
        stage("Start container") {
            steps {
               bat "docker-compose up -d --no-color --wait"
               bat "docker-compose ps"
            }

        }

    }

}