def gb
pipeline{
    agent any
    // environment{
    //     IMAGE_NAME="bahaddou88/my_ci_repo:v1.0.3"
        
    // }
    stages{
    stage("init"){
                steps(){
                script{
                      gb = load "GrouvyScript.groovy"
                      }
                }
        }
    stage("increment App Version "){
                steps(){
                script{
                      sh 'mvn build-helper:parse-version versions:set \
                      -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                      versions:commit'
                      def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                      def version = matcher[1][1]
                      env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                      echo "tag : ${env.IMAGE_NAME}"
                      }
                }
        }
    stage("test App"){   
                steps(){
                    script{
                    gb.test()
                    }
                }
        }
    stage("build JarFile"){
                when{
                    expression{
                        BRANCH_NAME=='docker-com'
                            }
                    }
                steps(){
                script{
                   gb.buildJarFile()
                   }
                }
        }
    stage("build dockerImage "){               
                when{
                    expression{
                        BRANCH_NAME=='docker-com'
                                            }
                }
            steps(){
                script{
                   gb.buildDockerImage()
                }
            }
    }
    stage("deploy"){
           when{
                    expression{
                        BRANCH_NAME=='docker-com'
                  }
                }
            steps(){
                script{
                   gb.deploy()
                }
        }
   }
   stage("commit version"){
     when{
                    expression{
                        BRANCH_NAME=='docker-com'
                  }
                }
    steps(){
        script{
            withCredentials([usernamePassword(credentialsId: 'gitlab-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]){
            //  sh 'git config --global user.email "jenkins@gmail.com"'
            //  sh 'git config --global user.name "jenkins"'

            sh 'git status'
            sh 'git branch'
            sh 'git config --list'
            sh "git remote set-url origin https://${USER}:${PASS}@gitlab.com/ayoub.bahaddouayoub/docker-jenkins-pipline.git"
            sh 'git add .'
            sh 'git commit -m "ci:version bump"'
            sh 'git push origin HEAD:docker-com'
           }
         }  
       }
     }
   }
 }
