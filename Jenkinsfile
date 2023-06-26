pipeline{
    agent any

    stages{
        stage('Build Artifact') {
            steps {
                sh "./mvnw clean package -DskipTests=true"
                archive 'target/*.jar'
            }
        }

        stage('Unit Test - JUnit and Jacoco') {
            steps {
                sh "./mvnw test"
            }
            post{
                always{
                    junit 'target/surefire-reports/*.xml'
                    jacoco execPattern: 'target/jacoco.exec'
                }
            }
        }

         stage('Docker build and Push') {
            steps {
                withDockerRegistry([credentialsId : "docker-hub", url :""]){
                    sh 'printenv'
                    sh 'docker build -t benkafirongo/numeric-app:""$GIT_COMMIT"" .'
                    sh 'docker push benkafirongo/numeric-app:""$GIT_COMMIT""'
                }
            }
         }
    }
}