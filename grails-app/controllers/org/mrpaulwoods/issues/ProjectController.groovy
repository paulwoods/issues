package org.mrpaulwoods.issues

class ProjectController {

	def beforeInterceptor = {
	    log.error ":"*99
	}

	def index = {
		render "ok"
	}
	
	def list = {
	}
	
	def create = {
	}
	
	def save = {
	}
	
	def show = {
	}
	
	def edit = {
	}
	
	def update = {
	}
	
	def delete = {
	}
	
	private def withProject(String id="id", Closure closure) {
		def project = Project.get(params[id])
		if(project) {
			closure.call project
		} else {
			flash.message = "The project with id of ${params[id]} was not found."
			redirect controller:"home", action:"index"
		}
	}
	
	
	
}
