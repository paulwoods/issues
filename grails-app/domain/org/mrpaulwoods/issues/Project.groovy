package org.mrpaulwoods.issues

class Project {
	
	String name
	
    static mapping = {
    	sort "name"
    }
    
    static constraints = {
    	name nullable:false, blank:false, unique:true, maxSize:100
    }
    
    def getIssues() {
    	Issue.findAllByProject this
    }
    
    static Project addProject(String name) {
    	def project = Project.findByName(name)
    	if(!project) {
    		project = new Project(name:name).save()
    	}
    	project
    }
    
    UserProject addUser(User user, UserProject.Access access) {
    	def up = UserProject.findByUserAndProject(user, this)
    	if(!up) {
    		up = new UserProject(user:user, project:this)
    	}
    	up.access = access
    	up.save()
    	up
    }
    
    
}
