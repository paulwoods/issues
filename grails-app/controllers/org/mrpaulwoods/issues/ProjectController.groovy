package org.mrpaulwoods.issues

class ProjectController {
	
	def pagination
	
	def index = {
	}
	
	def list = {
		
		pagination.fix params, "name", "asc"
		
		def projects = Project.createCriteria().list(params) {
		}
		
		[projects:projects]
		
	
	}
	
}
