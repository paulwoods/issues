package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(PasswordController)
@Mock([User,UserRole,Role,UserProject])
class PasswordControllerTests {

	
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
		assert "/password/password" == view
	}
	
	@Test
	public void change_failsSave() {
		def u1 = User.addUser("alpha")
		def cmd = new ChangePasswordCommand(user:u1,password:"",password1:"",password2:"")
		controller.change(cmd)
		
		assert "Unable to change the password." == controller.flash.message
		assert "/password/password" == view
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
		assert "/password/reset" == view
		assert u1 == model.user
	}

}
