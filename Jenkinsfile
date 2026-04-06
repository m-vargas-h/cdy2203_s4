pipeline {
    agent any

    tools {
        maven 'Maven 3'
        jdk 'JDK 17'
    }

    environment {
        SONAR_TOKEN = credentials('sonarqube-token')
    }

    stages {

        stage('Checkout') {
            steps {
                echo 'Clonando repositorio...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Compilando el proyecto...'
                sh 'mvn clean compile -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Ejecutando pruebas unitarias...'
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Ejecutando análisis estático con SonarQube...'
                withSonarQubeEnv('SonarQube') {
                    sh '''
                        mvn sonar:sonar \
                          -Dsonar.projectKey=backend-veterinaria \
                          -Dsonar.projectName="Backend Veterinaria CDY2203" \
                          -Dsonar.host.url=http://sonarqube:9000 \
                          -Dsonar.token=${SONAR_TOKEN}
                    '''
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo 'Verificando Quality Gate de SonarQube...'
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Package') {
            steps {
                echo 'Empaquetando la aplicación...'
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline ejecutado exitosamente.'
        }
        failure {
            echo '❌ Pipeline falló. Revisar los logs.'
        }
    }
}
