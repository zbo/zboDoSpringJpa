node {
   // Mark the code checkout 'stage'....
   //stage 'Checkout'
    echo 'start check out'
   // Get some code from a GitHub repository
   //git url: 'https://github.com/zbo/zboDoSpringJpa.git'
    echo 'finish check out'
   // Get the maven tool.
   // ** NOTE: This 'M3' maven tool must be configured
   // **       in the global configuration.
   def mvnHome = tool 'M3'

   // Mark the code build 'stage'....
   stage 'Build'
   // Run the maven build
   sh "${mvnHome}/bin/mvn clean install -Dmaven.test.skip=true"

   stage 'Coverage'
   sh "${mvnHome}/bin/mvn org.jacoco:jacoco-maven-plugin:prepare-agent clean install"
   sh "${mvnHome}/bin/mvn sonar:sonar"
}