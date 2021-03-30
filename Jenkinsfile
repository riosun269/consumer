#!groovy
pipeline {

    agent any
    tools {
        jdk 'Java8'
        maven 'Maven3.6.3'
    }

    environment {
        BRANCH_NAME = env.GIT_BRANCH.replace("origin/", "")
    }

    stages {
        stage('Initialize') {
            steps {
                sh '''
              echo "PATH = ${PATH}"
              echo "M2_HOME = ${M2_HOME}"
              echo "JAVA_HOME = ${JAVA_HOME}"
            '''
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean verify'
            }
        }
        stage('Publish Pacts') {
            steps {
                sh 'GIT_COMMIT=$(git log --format=format:%s -1)'
                //sh 'mvn pact:publish -Dpact.consumer.version=$GIT_COMMIT -Dpact.tag=${BRANCH_NAME} -Dpact.broker.url=http://riosun269.pactflow.io -Dpact.broker.token=FMGUYGlb1IMOAnLCBUNmOQ'
                sh 'mvn pact:publish -Dpact.consumer.version=$GIT_COMMIT -Dpact.broker.url=http://localhost:80'
            }
        }
        stage('Check Pact Verifications') {
          steps {
            sh 'curl -LO https://github.com/pact-foundation/pact-ruby-standalone/releases/download/v1.88.45/pact-1.88.45-osx.tar.gz'
            sh 'tar xzf pact-1.88.45-osx.tar.gz'
            dir('pact/bin') {
              sh './pact-broker can-i-deploy --retry-while-unknown=12 --retry-interval=10 -a messaging-app -b http://127.0.0.1:80 -e $GIT_COMMIT'
            }
          }
        }
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