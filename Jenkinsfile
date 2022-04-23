pipeline {
  agent any

  tools {
    gradle "Gradle"
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