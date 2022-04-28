
properties([disableConcurrentBuilds()])

pipeline {
  agent any

  tools {
    gradle "Gradle"
  }

  triggers { pollSCM('* * * * *') }

  options {
          buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
          timestamps()
      }

  stages {

    stage('Run test') {
      steps {
        git 'https://github.com/Ksevetest/mobileapp-creditprime.git'
        sh 'gradle build'
      }
    }
  }

  post {
      always {
        script {
          allure([
            includeProperties: false,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'allure-results']]
          ])
        }
      }
    }
}