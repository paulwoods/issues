package org.mrpaulwoods.issues

class User {

	def springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty("password")) {
			encodePassword()
		}
	}

	private void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
	
	static User addUser(String username) {
		def user = User.findByUsername(username)
		if(!user) {
			user = new User(username:username, password:username)
			user.save()
		}
		user
	}
	
}
