package org.mrpaulwoods.issues

class RegistrationController {

    def index() { 
    	redirect action:"register"
    }

    def register() {
    	[cmd:new RegisterCommand()]
    }
    
    def install = { RegisterCommand cmd ->
    
    	if(cmd.hasErrors()) {
    	
			log.error "unable to register user $cmd.username"
    		cmd.errors.allErrors.each { 
    			log.error message(error:it)
    		}
    		
    		flash.message = "Unable to register the user."
    		render view:"register", model:[cmd:cmd]
    		return
    	}
    	
    	def user = new User(username:cmd.username, password:cmd.password1)
		if(user.validate() && user.save()) {

			UserRole.addUserRole user, Role.user

			log.info "registered user $user.username"

			flash.message = "User $user.username was registered."
			redirect controller:"home", action:"index"
		} else {
		
			log.error "unable to save user $user.username"
   			cmd.errors.allErrors.each { 
    			log.error message(error:it)
    		}
    		
			flash.message = "Unable to register the user."
			render view:"register", model:[cmd:cmd]
		}
    	
    }
}

class RegisterCommand {
	String username
	String password1
	String password2
	
	static constraints = {
		username nullable:false, blank:false
		password1 nullable:false, blank:false, validator: { val, obj ->
    		obj.password1 == obj.password2
		}
		password2 nullable:false, blank:false
	}
	
}

