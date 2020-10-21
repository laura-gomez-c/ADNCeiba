pipeline {
    agent {
        label 'Slave_Mac'
    }
      
    options { 
        buildDiscarder(logRotator(numToKeepStr: '3')) 
        disableConcurrentBuilds()       
    }
   
    tools {     
        jdk 'JDK8_Mac' 
    } 

    stages{     
        stage('Checkout'){ 
            steps{ 
                echo "------------>Checkout<------------" 
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'GitHub_laura-gomez-c', url:'https://github.com/laura-gomez-c/parqueadero-ceiba.git']]]) 
                sh 'chmod +x ADNParquedero/gradlew'
            } 
        }     
	

	stage('Build') { 
            steps{     
				echo "------------>Build<------------" 
                dir ('ADNParquedero/'){
                    sh './gradlew clean build'
                }
            } 
        }
		
		stage('Unit Tests') { 
			steps{
				echo "------------>Unit Tests<------------" 
				dir ('ADNParquedero/'){
					sh './gradlew --b build.gradle test --scan'
					sh './gradlew --b build.gradle jacocoTestReport'
				}
			}
		}
		
        stage('Static Code Analysis') {   
            steps{    
				echo "------------>Static Code Analysis<------------" 
                withSonarQubeEnv('Sonar'){
                    sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"
                }       
            }     
        }
    } 

    post { 
        always {  
            echo 'This will always run' 
        }     
        success {  
            echo 'This will run only if successful' 
            junit '**/test-results/test/*.xml'
        }  
        failure {   
            echo 'This will run only if failed'
            mail(to: 'laura.gomez@ceiba.com.co',
                subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                body: "Something is wrong with ${env.BUILD_URL}")
        }    
        unstable {      
            echo 'This will run only if the run was marked as unstable'    
        }     
        changed {      
            echo 'This will run only if the state of the Pipeline has changed'  
            echo 'For example, if the Pipeline was previously failing but is now successful'  
        } 
    } 
}