pipeline {
    agent any
    tools {
        maven "MAVEN"
    }


    stages {
        stage('Prep'){
            steps{
                sh 'ls -l'
            }
        }
        stage('Scan'){
            steps{
                withSonarQubeEnv(installationName: 'Sonarqube_on_Target1'){
                    sh 'mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
                }
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
                            rm -rf 'Artifacts_jenkins_backend'
                            git clone https://gitlab.com/jenkins5523910/Artifacts_jenkins_backend.git
                            cd Artifacts_jenkins_backend
                            cp ../target/*.war .
                            git add .
                            git commit -m "New artifact $(date '+%Y-%m-%d -%H:%M')"
                            git push
                            rm -rf 'Artifacts_jenkins_backend'
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
