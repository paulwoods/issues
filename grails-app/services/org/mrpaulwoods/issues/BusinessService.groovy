package org.mrpaulwoods.issues

public class BusinessService {

	boolean userCanDelete(User user, Project project) {
	
		def userProject = UserProject.findByUserAndProject(user, project)
		if(!userProject) {
			return false
		}
		
		if(UserProject.Access.Admin == userProject.access) {
			return true
		}
		
		return false
	
	}
	
}
