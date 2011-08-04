package org.mrpaulwoods.issues

class User {

	def springSecurityService
	
	static transients = [ "authorities","userProjects","userRoles" ]
	
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
	
	def beforeDelete() {
		User.withNewSession {
			userRoles*.delete()
			userProjects*.delete()
		}
	}
	
	private void encodePassword() {
		if(springSecurityService) { 
			password = springSecurityService.encodePassword(password)
		}
	}
	
	void changePasswordToUsername() {
		log.debug "resetting password for $username"
		password = username
	}
	
	
	static User addUser(String username) {
		def user = User.findByUsername(username)
		if(!user) {
			user = new User(username:username, password:username)
			user.save()
			
			user.log.debug "added user ${user.dump()}"
		}
		user
	}
	
	List<UserRole> getUserRoles() {
		UserRole.findAllByUser this
	}
	
	List<UserProject> getUserProjects() {
		UserProject.findAllByUser this
	}
	
	
}
