package org.mrpaulwoods.issues

class Project {
	
	String name
	
    static mapping = {
    	sort "name"
    }
    
    static constraints = {
    	name nullable:false, blank:false, unique:true, maxSize:100
    }
    
	def beforeDelete() {
		Project.withNewSession {
			UserProject.findAllByProject(this)*.delete(flush:true)
		}
	}
	
    List<UserProject> getUserProjects() {
    	UserProject.findAllByProject(this).findAll { userProject -> 
    		userProject.canAccess() 
    	}
    }

    List<User> getUsers() {
    	userProjects.user
    }


    static Project addProject(String name) {
    	def project = Project.findByName(name)
    	if(!project) {
    		project = new Project(name:name).save()
    		
    		project.log.debug "added project ${project.dump()}"
    	}
    	project
    }
    
    UserProject addUser(User user, UserProject.Access access) {
    	def up = UserProject.findByUserAndProject(user, this)
    	if(!up) {
    		up = new UserProject(user:user, project:this)
    		
    		log.debug "added user $user.username to project $name"
    	}
    	up.access = access
    	up.save()
    	up
    }
    
    
    
}
