package org.mrpaulwoods.issues

public class BusinessService {

	boolean userCanDelete(User user, Project project) {
		
		if(isSystemAdmin(user)) {
			return true
		}
		
		if(isProjectAdmin(user, project)) {
			return true
		}
		
		return false
	}
	
	private boolean isSystemAdmin(User user) {
		null != UserRole.findByUserAndRole(user, Role.findByAuthority("ROLE_ADMIN"))
	}
	
	private boolean isProjectAdmin(User user, Project project) {
		UserProject.findByUserAndProject(user, project)?.access == UserProject.Access.Admin
	}
	
	
}
