package org.mrpaulwoods.issues

class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
	
	static Role addRole(String authority) {
		def role = Role.findByAuthority(authority)
		if(!role) {
			role = new Role(authority:authority).save()
		}
		role
	}
	
}