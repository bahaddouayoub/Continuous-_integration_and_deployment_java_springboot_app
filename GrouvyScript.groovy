
    def test(){
            echo "this is the testl step with function define inside scrip.groovy "
        }
    
    def buildJarFile(){
            echo "this is the build jar file step with function define inside scrip.groovy using $BRANCH_NAME "
            sh "mvn clean package"
        }

    def buildDockerImage(){
        echo "build Docker image name:${IMAGE_NAME}"
        sh "docker build -t bahaddou88/my_ci_repo:${IMAGE_NAME} ."
        withCredentials([usernamePassword(credentialsId: 'docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push bahaddou88/my_ci_repo:${IMAGE_NAME}"
    }
    
     }

    def deploy(){
            echo "deploy docker image using docker-compose tool inside Ec2 instance"

            def dockerComposeCommand="docker-compose up --detach"
            def ec2Instance="ec2-user@3.122.104.117"

            sshagent(['aws-credentials-account']) {
                sh "scp docker-compose.yaml  ${ec2Instance}:/home/ec2-user"
                sh "ssh -o StrictHostKeyChecking=no  ${ec2Instance} ${dockerComposeCommand}"
    }
        }

    return this
