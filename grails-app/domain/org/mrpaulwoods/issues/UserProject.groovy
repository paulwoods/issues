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


}

