pipeline {
    agent { label 'server-developement' }
    tools {
        maven "MAVEN"
    }

    // environment {
    //     TARGET_MACHINE = "${params.TARGET_USER}@${params.TARGET_IP}"
    // }

    stages {
    // stage('Checkout') {
    //     steps {
    //         git url: 'https://gitlab.com/jenkins5523910/Ehealth_backend_package_dev.git', branch: 'main'
    //     }
    //}

        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }

        // stage('Deploy') {
        //     steps {
        //         sh '''
        //         JAR_FILE=target/ehealth-back-1.0.jar
        //         scp $JAR_FILE $TARGET_MACHINE:$TARGET_DIR
        //         ssh $TARGET_MACHINE "cd $TARGET_DIR && java -jar $JAR_FILE"
        //         '''
        //     }
        // }

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

        stage('git push') {
            steps {
                withCredentials([
                    gitUsernamePassword(credentialsId: 'GitlabPasswordUser', gitToolName: 'Default')
                    ]) {
                        sh '''
                            # modify some file
                            rm -rf Artifacts_jenkins_backend
                            git clone https://gitlab.com/jenkins5523910/Artifacts_jenkins_backend.git
                            cd Artifacts_jenkins_backend
                            git checkout main
                            pwd
                            cp ../target/*.war .
                            ls -la
                            git add .
                            git config --global user.email "sebastien.maffeis@gmail.com"
                            git config --global user.name "sebastien.maffeis"
                            git commit -m "New artifact"
                            git push -u origin main
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
