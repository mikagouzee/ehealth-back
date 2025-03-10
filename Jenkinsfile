pipeline {
    agent { label 'server-developement' }
    tools {
        maven "MAVEN"
    }
    stages {
           stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('PMD') {
            steps {
                // recordIssues sourceCodeRetention: 'LAST_BUILD', tools: [pmdParser(), checkStyle(), findBugs()]
                sh 'mvn pmd:pmd'
            }
        }
        stage('PMD') {
            steps {
                sh 'mvn checkstyle:checkstyle'
            }
        }
    }
    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }

    }
}
