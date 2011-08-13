package org.mrpaulwoods.issues

class PasswordController {

    def password() {
    	withUser { user ->
    		[cmd:new ChangePasswordCommand(user:user)]
    	}
    }
    
    def change(ChangePasswordCommand cmd) {
    	
    	if(cmd.hasErrors()) {
    		cmd.errors.allErrors.each { 
    			log.error it
    		}
    		
    		flash.message = "Unable to change the password."
    		render view:"password", model:[cmd:cmd]
    		return
    	}
    	
    	def user = cmd.user
    	user.password = cmd.password1
    	
		if(user.validate() && user.save()) {

			log.debug "change password user $user.username"

			flash.message = "User $user.username password was changed."
			redirect controller:"user", action:"show", id:user.id
			
		} else {
			flash.message = "Unable to change the password."
			render view:"password", model:[cmd:cmd]
		}
		
	}
    
    def reset() {
    	withUser { user ->
    		[user:user]
    	}
    }
    
    def reset_password() {
    
    	withUser { user ->
    	
			user.changePasswordToUsername()

			if(user.validate() && user.save()) {

				log.debug "reset password user $user.username"

				flash.message = "User $user.username password was reset."
				redirect controller:"user", action:"show", id:user.id

			} else {
				flash.message = "Unable to reset the password."
				render view:"reset", model:[user:user]
			}

    	}
    	
    }
 
    private def withUser(String id="id", Closure closure) {
    	def user = User.get(params[id])
    	if(user) {
    		closure.call user
    	} else {
    		flash.message = "The user was not found."
    		redirect controller:"home", action:"index"
    	}
    }
}


class ChangePasswordCommand {

	User user
	String password
	String password1
	String password2
	
	static constraints = {
		password nullable:false, blank:false, validator: { val, obj ->
    		obj.user.password == obj.user.springSecurityService.encodePassword(obj.password)
		}
		password1 nullable:false, blank:false, validator: { val, obj ->
    		obj.password1 == obj.password2
		}
		password2 nullable:false, blank:false
	}
	
}
