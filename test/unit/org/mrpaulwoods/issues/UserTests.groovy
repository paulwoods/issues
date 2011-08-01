package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(User)
@Mock(UserRole)
class UserTests {
	
	@Before
	public void setup() {
	}
	
	@After
	public void tearDown() {
	}
	
    @Test
    public void addUser_new() {
		boolean called = false
		User.metaClass.static.encodePassword = { -> called = true}

		assert 0 == User.count()
		User.addUser("paulwoods")
		assert 1 == User.count()
		assert true == called

		GroovySystem.metaClassRegistry.removeMetaClass User.class
    }

    @Test
    public void addUser_exists() {

		boolean called = false
		User.metaClass.static.encodePassword = { -> called = true}
		
		def u1 = User.addUser("paulwoods")
		def u2 = User.addUser("paulwoods")
		assert 1 == User.count()
		assert u2 == u1
		assert true == called
		
		GroovySystem.metaClassRegistry.removeMetaClass User.class
    }
    
    @Test
    public void encodePassword() {
    	
    	def user = new User(username:"abc", password:"123")
    	
    	def springSecurityService = mockFor(grails.plugins.springsecurity.SpringSecurityService)
    	springSecurityService.demand.encodePassword(1..1) { p -> p*2 }
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
    
    
}

