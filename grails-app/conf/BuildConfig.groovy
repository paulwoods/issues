grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {

	inherits("global") {
	}
	
	log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'

	checksums true // Whether to verify checksums on resolve

	repositories {
		inherits true // Whether to inherit repository definitions from plugins
		
		grailsPlugins()
		grailsHome()
		grailsCentral()
		mavenLocal()
		mavenCentral()
	}
	
	dependencies {
		runtime 'mysql:mysql-connector-java:5.1.16'
	}

	plugins {
		compile ":hibernate:$grailsVersion"
		compile ":jquery:1.6.1.1"
		compile ":resources:1.0.1"
		build ":tomcat:$grailsVersion"
	}
}

codenarc.reports = {
	MyXmlReport('xml') {                    
		outputFile = 'target/CodeNarcReport.xml'
		title = 'Issues'             
	}

	MyHtmlReport('html') {                  
		outputFile = 'target/CodeNarcReport.html'
		title = 'Issues'
	}
}

coverage {
	exclusions = ["**/LoginController*", "**/LogoutController*" ]
}

