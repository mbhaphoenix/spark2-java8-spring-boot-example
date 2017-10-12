node('build') {
	stage ('Git Checkout') {
		checkout scm
	}		
	   
	stage ('Sonar') {
		sonar{
		}
	}
		
	stage('Deploy') {
		deployArtefact {
	       	skipTests = 'false'
		}
	} 
}