#!groovy
pipeline {

  agent any

  environment {
      BRANCH_NAME=env.GIT_BRANCH.replace("origin/", "master")
  }

  stages {
    stage('Build') {
      steps {
        dir('messaging-app') {
           sh 'mvn clean verify'
        }
      }
    }
    stage('Publish Pacts') {
      steps {
        dir('messaging-app') {
           sh 'mvn pact:publish -Dpact.consumer.version=${GIT_COMMIT} -Dpact.tag=${BRANCH_NAME} -Dpact.broker.url=http://riosun269.pactflow.io -Dpact.broker.token=FMGUYGlb1IMOAnLCBUNmOQ'
        }
      }
    }
    /*stage('Check Pact Verifications') {
      steps {
        sh 'curl -LO https://github.com/pact-foundation/pact-ruby-standalone/releases/download/v1.61.1/pact-1.61.1-linux-x86_64.tar.gz'
        sh 'tar xzf pact-1.61.1-linux-x86_64.tar.gz'
        dir('pact/bin') {
          sh "./pact-broker can-i-deploy --retry-while-unknown=12 --retry-interval=10 -a messaging-app -b http://pact_broker -e ${GIT_COMMIT}"
        }
      }
    }*/
    stage('Deploy') {
      when {
        branch 'master'
      }
      steps {
        echo 'Deploying to prod now...'
      }
    }
  }
}