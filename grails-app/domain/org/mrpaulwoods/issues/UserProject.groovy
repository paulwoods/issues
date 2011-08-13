package org.mrpaulwoods.issues

import groovy.transform.ToString
	
@ToString
class UserProject {

	User user
	Project project
	Access access

	static mapping = {
	}

	static constraints = {
	}

	static enum Access {
		None,
		Read,
		Edit,
		Admin,
	}
	
	boolean canAccess() {
    	[Access.Admin, Access.Read, Access.Edit].contains(access)
	}
	
	static UserProject add(User user, Project project, Access access) {
		def up = UserProject.findByUserAndProject(user, project)
		if(!up) {
			up = new UserProject(user:user, project:project, access:access)
			up.save()
		}
		up
	}

}

