pipeline {
    agent any
    tools {
        gradle 'gradle7.6.1'
    }
    stages {
        stage('Build Gradle') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Vaibhav-RP/EV-Charging-Station-Web-App']])
                sh 'gradle clean build'
            }
        }
        stage('Build Docker image') {
            steps {
                script {
                    sh ' docker build -t vaibhavrp/evstationdata .'
                }
            }
        }
        stage('Push image to Docker Hub') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub', variable: 'dockerhubpwd')]) {
                    sh "docker login -u vaibhavrp -p ${dockerhubpwd}"
                }
                sh 'docker push vaibhavrp/evstationdata'
            }
        }
    }
    
}