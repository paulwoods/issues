package org.mrpaulwoods.issues

import grails.plugins.springsecurity.Secured

class UserController {

	def pagination

    def index() {
    	redirect action:"list", params:"params"
    }
    
	@Secured(['ROLE_USER','ROLE_ADMIN'])
    def list() {
		pagination.fix params, "username", "asc"
		def users = User.createCriteria().list(params) { }
		[users:users]
    }
    
    def create() {
    	[user:new User()]
    }
    
    def save() {
    	def user = new User(params)
    	user.changePasswordToUsername()
    	
    	if(user.validate() && user.save()) {
    	
    		UserRole.addUserRole user, Role.user
    		
    		log.debug "created user $user.username"
    		
    		flash.message = "User $user.username was created."
    		redirect action:"show", id:user.id
    	} else {
    		flash.message = "Unable to save the user."
    		render view:"create", model:[user:user]
    	}
    }
    
    def show() {
    	withUser { user ->
    		[user:user]
    	}
    }
    
    def edit() {
    	withUser { user ->
    		[user:user]
    	}
    }
    
    def update() {
    	withUser { user ->
			user.properties = params
			
			if(user.validate() && user.save()) {

				log.debug "updated user $user.username"

				flash.message = "User $user.username was updated."
				redirect action:"show", id:user.id
			} else {
				flash.message = "Unable to update the user."
				render view:"update", model:[user:user]
			}

    	}
    }
    
    def delete() {
    	withUser { user ->
    		user.delete()
    		log.debug "deleted user $user.username"
    		flash.message = "User $user.username was deleted."
    		redirect action:"list"
    	}
    }

    def register() {
    	[cmd:new RegisterCommand()]
    }
    
    def install = { RegisterCommand cmd ->
    	if(cmd.hasErrors()) {
    		cmd.errors.allErrors.each { 
    			log.error it
    		}
    		
    		flash.message = "Unable to register the user"
    		render view:"register", model:[cmd:cmd]
    		return
    	}
    	
    	def user = new User(username:cmd.username, password:cmd.password1)
		if(user.validate() && user.save()) {

    		UserRole.addUserRole user, Role.user

			log.debug "registered user $user.username"

			flash.message = "User $user.username was registered."
			redirect action:"show", id:user.id
		} else {
			flash.message = "Unable to registered the user."
			render view:"register", model:[cmd:cmd]
		}
    	
    }
	    
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
    		
    		flash.message = "Unable to change the password"
    		render view:"password", model:[cmd:cmd]
    		return
    	}
    	
    	def user = cmd.user
    	user.password = cmd.password1
    	
		if(user.validate() && user.save()) {

			log.debug "change password user $user.username"

			flash.message = "User $user.username password was changed."
			redirect action:"show", id:user.id
			
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
				redirect action:"show", id:user.id

			} else {
				flash.message = "Unable to reset the password."
				render view:"reset", model:[cmd:cmd]
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

