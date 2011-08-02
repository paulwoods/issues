package org.mrpaulwoods.issues

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
	

}

