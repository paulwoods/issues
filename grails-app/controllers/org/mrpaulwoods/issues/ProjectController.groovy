package org.mrpaulwoods.issues

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER','ROLE_ADMIN'])
class ProjectController {
	
	def pagination
	def springSecurityService
	def businessService
	
	def index() {
		redirect action:"list", params:params
	}
	
	def list() {
		pagination.fix params, "name", "asc"
		def projects = Project.createCriteria().list(params) { }
		[projects:projects]
	}
	
	def create() {
		[project : new Project()]
	}
	
	def save() {
		def project = new Project(params)
		
		if(!project.validate() || !project.save()) {
		
			log.error "Unable to save the project."
			
			project.errors.allErrors.each {
				log.error it
			}

			render view:"create", model:[project:project]
			return
		}
		
		project.addUser springSecurityService.currentUser, UserProject.Access.Admin

		flash.message = "The project $params.name has been created."
		redirect action:"show", id:project.id
	}
	
	def show() {
		withProject { project ->
			[project:project]
		}
	}
	
	def edit() {
		withProject { project ->
			[project:project]
		}
	}
	
	def update() {
		withProject { project ->
			project.properties = params

			if(!project.validate() || !project.save()) {

				log.error "Unable to update the project."

				project.errors.allErrors.each {
					log.error it
				}

				render view:"edit", model:[project:project]
				return
			}
			
			flash.message = "The project $params.name has been updated."
			redirect action:"show", id:project.id
		}
		
	}
	
	def delete() {
	
		withProject { project ->
		
			if(!businessService.userCanDelete(springSecurityService.currentUser, project)) {
				flash.message = "You cannot delete the project."
				redirect action:"show", id:project.id
				return
			}
			
			project.userProjects*.delete()
			
			project.delete()
			
			flash.message = "The project $project.name has been deleted."
			redirect action:"list"
		}
	}
	
	private def withProject(String id="id", Closure closure) {
		def project = Project.get(params[id])
		if(project) {
			closure.call project
		} else {
			flash.message = "The project was not found."
			redirect controller:"home", action:"index"
		}
	}
	
	
}
