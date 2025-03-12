pipeline {
    agent { label 'server-developement' }
    tools {
        maven "MAVEN"
    }


    stages {
        stage('Static Analysis') {
            steps {
                sh 'mvn pmd:pmd'

            recordIssues(
                tools: [
                    pmdParser(pattern: 'target/pmd.xml')
                ]
            )
            }
        }
        stage('Errors report') {
            steps {
                emailext(
                    body: 'Errors_report',
                    subject: 'Errors_report',
                    to: 'emailextjenkins@gmail.com',
                    attachmentsPattern: '**/target/pmd.xml'
                )
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('git push') {
            steps {
                withCredentials([
                    gitUsernamePassword(credentialsId: 'GitlabPasswordUser', gitToolName: 'Default')
                    ]) {
                        sh '''
                            git clone https://gitlab.com/jenkins5523910/Artifacts_jenkins_backend.git
                            cd Artifacts_jenkins_backend
                            cp ../target/*.war .
                            git add .
                            git config --global user.email "sebastien.maffeis@gmail.com"
                            git config --global user.name "sebastien.maffeis"
                            git commit -m "New artifact"
                            git push
                        '''
                    }
            }
        }
    }

    post {
        failure {
            echo 'Deployment failed!'
        }

        success {
            echo 'Deployment successful!'
        }
    }
}
