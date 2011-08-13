package org.mrpaulwoods.issues

import grails.plugins.springsecurity.Secured

@Secured(["isAuthenticated()"])
class UserController {

	def pagination
	def springSecurityService
	def controllerHelper
	
	def beforeInterceptor = {
		log.debug "#"*80
		log.debug "login user=" + springSecurityService.currentUser?.username
		log.debug "params=" + params
	}
	
    def index() {
    	redirect action:"list", params:"params"
    }
    
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
    	
    		boolean selfDelete = springSecurityService.currentUser.id == user.id
    	
    		user.delete()
    		log.debug "deleted user $user.username"
    		flash.message = "User $user.username was deleted."
    		
    		if(selfDelete) {
    			redirect controller:"logout"
    		} else {
    			redirect action:"list"
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
    
    def join() {
    	withUser { user ->
    		[user:user]
    	}
    }
    
    def attach() {
    
    	withUser { user ->
			
			User.withTransaction { status ->
				
				user.userProjects*.delete()				
				
				controllerHelper.getListOfLongs(params.selected).each { project_id ->
    				println "XXXXXXXXXXXXXXXXXXXXXXXX"
    				UserProject.add user, Project.get(project_id), UserProject.Access.Admin
    				
				}
				
			}
			
			redirect action:"show", id:user.id

    	}
    	
    }

}

