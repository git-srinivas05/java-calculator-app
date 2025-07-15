pipeline {
    agent any

    environment {
        AWS_REGION = "us-east-1"
        IMAGE_NAME = "java-app"
        ECR_REGISTRY = "211218518678.dkr.ecr.us-east-1.amazonaws.com"
        ECR_REPO_URI = "${ECR_REGISTRY}/${IMAGE_NAME}"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main', 'https://github.com/git-srinivas05/java-calculator-app.git'
            }
        }

        stage('Build Java App') {
            steps {
                sh 'javac App.java'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t $ECR_REPO_URI:latest .'
            }
        }

        stage('Authenticate with AWS ECR') {
            steps {
                withCredentials([[
                    $class: 'AmazonWebServicesCredentialsBinding',
                    credentialsId: 'AWS-credentails'
                ]]) {
                    sh '''
                        aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REGISTRY
                    '''
                }
            }
        }

        stage('Push Docker Image to ECR') {
            steps {
                sh 'docker push $ECR_REPO_URI:latest'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh '''
                    kubectl apply -f k8s/deployment.yaml
                    kubectl apply -f k8s/service.yaml
                '''
            }
        }
    }

    post {
        success {
            echo "✅ Deployment completed successfully!"
        }
        failure {
            echo "❌ Pipeline failed. Please check the logs."
        }
    }
}
