package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(RegistrationController)
@Mock([User,UserRole,Role,UserProject])
class RegistrationControllerTests {

	@Before
	public void setup() {
		Role.addRole("ROLE_USER")
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
		assert "/home/index" == response.redirectedUrl

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
		assert "/registration/register" == view
		assert cmd == model.cmd
	}
	
	
	@Test
	public void install_failSave() {
		
		def u1 = User.addUser("alpha")
		
		def cmd = new RegisterCommand(username:"",password1:"alpha",password2:"alpha")
		controller.install(cmd)
		
		assert "Unable to register the user." == controller.flash.message
		assert "/registration/register" == view
		assert cmd == model.cmd
	}

}
