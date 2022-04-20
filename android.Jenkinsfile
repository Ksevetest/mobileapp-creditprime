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
}