pipeline {
    agent any

    // parameters {
    //     string(name: 'TARGET_USER', defaultValue: 'user', description: 'SSH User for the target machine')
    //     string(name: 'TARGET_IP', defaultValue: '0.0.0.0', description: 'IP Address of the target machine')
    //     string(name: 'TARGET_DIR', defaultValue: '/path/to/deployment/directory', description: 'Deployment directory on the target machine')
    // }

    // environment {
    //     TARGET_MACHINE = "${params.TARGET_USER}@${params.TARGET_IP}"
    // }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/mikagouzee/ehealth-back.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -Dmaven.test.skip'
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
