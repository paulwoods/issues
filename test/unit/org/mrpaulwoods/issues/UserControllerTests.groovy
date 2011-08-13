package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(UserController)
@Mock([User,UserRole,Role,UserProject])
class UserControllerTests {
	
	@Before
	public void setup() {
		Role.addRole("ROLE_USER")
	}	
	
    @Test
    public void index() {
    	controller.index()
    	assert "/user/list" == response.redirectedUrl
    }
    
	@Test
	public void list() {
		def pagination = mockFor(org.mrpaulwoods.issues.Pagination)
		pagination.demand.fix(1..1) { Map p, String c, String o -> }
		controller.pagination = pagination.createMock()
		
		def u1 = User.addUser("alpha")
		
		def model = controller.list()
		assert 1 == model.users.size()
		assert model.users.contains(u1)
		
		pagination.verify()
	}
	
	@Test
	public void create() {
		def model = controller.create()
		assert model.user instanceof User
	}

	@Test
	public void save_success() {
		
		params.username = "alpha"
		
		controller.save()
		
		assert "User alpha was created." == controller.flash.message
		assert "/user/show/1" == response.redirectedUrl
		
		assert 1 == User.count()
		
		def user = User.list()[0]
		
		assert "alpha" == user.username
		assert null != user.password
		assert 1 == user.userRoles.size()
		assert user.userRoles.role.contains(Role.user)
	}
	
	@Test
	public void save_failure() {
		controller.save()
		
		assert "Unable to save the user." == controller.flash.message
		assert "/user/create" == view
		assert model.user instanceof User
	}
	
	@Test
	public void show() {
		def u1 = User.addUser("alpha")
		controller.params.id = u1.id
		def model = controller.show()
		assert u1 == model.user
	}
	
	@Test
	public void edit() {
		def u1 = User.addUser("alpha")
		controller.params.id = u1.id
		def model = controller.edit()
		assert u1 == model.user
	}
	
	@Test
	public void update_success() {
		def u1 = User.addUser("alpha")
		controller.params.username = "beta"
		controller.params.id = u1.id
		
		controller.update()
		
		assert "User beta was updated." == controller.flash.message
		assert "/user/show/1" == response.redirectedUrl
	}
		
	@Test
	public void update_fail() {
		def u1 = User.addUser("alpha")
		controller.params.username = null
		controller.params.id = u1.id
		
		controller.update()
		
		assert "Unable to update the user." == controller.flash.message
		assert "/user/update" == view
		assert u1 == model.user
	}
	
	@Test
	public void delete() {
	
		controller.springSecurityService = [ currentUser : [ id : -1 ] ]
	
	
		def u1 = User.addUser("alpha")
		controller.params.id = u1.id
		
		controller.delete()
		
		assert "User alpha was deleted." == controller.flash.message
		assert "/user/list" == response.redirectedUrl
		assert 0 == User.count()
	}
	
	@Test
	public void withUser_success() {
	
		def u1 = User.addUser("alpha")
		controller.params.id = u1.id
		
		def user
		controller.withUser { u ->
			user = u
		}
		
		assert user == u1

	}
	
	@Test
	public void withUser_fail() {
		def u1 = User.addUser("alpha")
		controller.params.id = 12345
		controller.withUser { u -> }
		
		assert "The user was not found." == controller.flash.message
		assert "/home/index" == response.redirectedUrl
	}
		
}

