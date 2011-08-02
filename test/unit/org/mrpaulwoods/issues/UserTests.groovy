package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(User)
@Mock([UserRole,Role])
class UserTests {
	
    @Test
    public void addUser_new() {
		assert 0 == User.count()
		User.addUser("paulwoods")
		assert 1 == User.count()
    }

    @Test
    public void addUser_exists() {
		def u1 = User.addUser("paulwoods")
		def u2 = User.addUser("paulwoods")
		assert 1 == User.count()
		assert u2 == u1
    }
    
    @Test
    public void encodePassword() {
    	
    	def user = new User(username:"abc", password:"123")
    	
    	def springSecurityService = mockFor(grails.plugins.springsecurity.SpringSecurityService)
    	springSecurityService.demand.asBoolean(1..1) { -> true }
    	springSecurityService.demand.encodePassword(1..1) { String p -> p*2 }
    	user.springSecurityService = springSecurityService.createMock()
    	
    	user.encodePassword()
    	
    	assert "123123" == user.password
    	
    	springSecurityService.verify()
    }
    
    @Test
    public void beforeUpdate_clean() {
    	def user = new User(password:"123")
    	user.metaClass.isDirty = { field -> false }
    	user.beforeUpdate()
    	assert "123" == user.password
    }
    
    @Test
    public void beforeUpdate_dirty() {
    	def user = new User(password:"123")
    	user.metaClass.isDirty = { field -> true }
    	user.metaClass.encodePassword = { -> password = password*2 }
    	user.beforeUpdate()
    	assert "123123" == user.password
    }
    
    @Test
    public void authorities() {
    	def r1 = Role.addRole("ROLE1")
    	def r2 = Role.addRole("ROLE2")
    	
    	def u1 = User.addUser("user1")
    	
    	UserRole.addUserRole(u1,r1)
    	UserRole.addUserRole(u1,r2)
    	
    	def authorities = u1.authorities
    	assert 2 == authorities.size()
    	assert authorities.contains(r1)
    	assert authorities.contains(r2)
    }
    
}

