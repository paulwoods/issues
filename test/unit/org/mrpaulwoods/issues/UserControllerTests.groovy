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
		def pagination = mockFor(org.mrpaulwoods.pagination.Pagination)
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
		def u1 = User.addUser("alpha")
		controller.params.id = u1.id
		
		controller.delete()
		
		assert "User alpha was deleted." == controller.flash.message
		assert "/user/list" == response.redirectedUrl
		assert 0 == User.count()
	}
	
	@Test
	public void register() {
		def model = controller.register()
		assert model.cmd instanceof RegisterCommand
	}
	
	@Test
	public void install_success() {
		
		def cmd = new RegisterCommand(username:"alpha",password1:"secr3t",password2:"secr3t")
	
		controller.install(cmd)
		
		assert "User alpha was registered." == controller.flash.message
		assert "/user/show/1" == response.redirectedUrl

		assert 1 == User.count()
		def user = User.list()[0]
		assert "alpha" == user.username
		assert null != user.password
		
		assert 1 == UserRole.count()
		
		assert 1 == user.userRoles.size()
		assert user.userRoles.role.contains(Role.user)
	}
	
	@Test
	public void install_failValidation() {
		
		def cmd = new RegisterCommand(username:"",password1:"",password2:"")
		cmd.validate()
		controller.install(cmd)
		
		assert "Unable to register the user." == controller.flash.message
		assert "/user/register" == view
		assert cmd == model.cmd
	}
	
	
	@Test
	public void install_failSave() {
		
		def u1 = User.addUser("alpha")
		
		def cmd = new RegisterCommand(username:"",password1:"alpha",password2:"alpha")
		controller.install(cmd)
		
		assert "Unable to register the user." == controller.flash.message
		assert "/user/register" == view
		assert cmd == model.cmd
	}
	
	@Test
	public void password() {
		def u1 = User.addUser("alpha")
		controller.params.id = u1.id
		def model = controller.password()
		assert model.cmd instanceof ChangePasswordCommand
	}
	
	@Test
	public void change_success() {
		def u1 = User.addUser("alpha")
		def cmd = new ChangePasswordCommand(user:u1,password:"alpha",password1:"beta",password2:"beta")
		controller.change(cmd)
		
		assert "User alpha password was changed." == controller.flash.message
		assert "/user/show/1" == response.redirectedUrl
	}
	
	@Test
	public void change_failsValidation() {
		def cmd = new ChangePasswordCommand(user:null,password:"",password1:"1",password2:"2")
		cmd.validate()
		controller.change(cmd)
		
		assert "Unable to change the password." == controller.flash.message
		assert "/user/password" == view
	}
	
	@Test
	public void change_failsSave() {
		def u1 = User.addUser("alpha")
		def cmd = new ChangePasswordCommand(user:u1,password:"",password1:"",password2:"")
		controller.change(cmd)
		
		assert "Unable to change the password." == controller.flash.message
		assert "/user/password" == view
	}
	
	@Test
	public void reset() {
		def u1 = User.addUser("alpha")
		controller.params.id = u1.id
		def model = controller.reset()
		assert u1 == model.user
	}
	
	@Test
	public void reset_password_success() {
		def u1 = User.addUser("alpha")
		def password = u1.password
		
		u1.password = "123"
		controller.params.id = u1.id
		
		controller.reset_password()
		
		assert password == u1.password
		
		assert "User alpha password was reset." == controller.flash.message
		assert "/user/show/1" == response.redirectedUrl
	}
	
	@Test
	public void reset_password_fail() {
		
		def u1 = User.addUser("alpha")
		u1.username=""
		
		controller.metaClass.withUser = { Closure c ->	
			c.call u1
		}
		
		controller.reset_password()
		
		assert "Unable to reset the password." == controller.flash.message
		assert "/user/reset" == view
		assert u1 == model.user
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

