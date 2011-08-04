package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(ProjectController)
@Mock([Project,Issue,UserProject,User,UserRole])
class ProjectControllerTests {

    @Test
    public void index() {
    	controller.index()
    	assert "/project/list" == response.redirectedUrl
    }
    
    @Test
    public void list() {
    	def pagination = mockFor(org.mrpaulwoods.pagination.Pagination)
    	pagination.demand.fix(1..1) { Map p, String c, String o -> }
    	controller.pagination = pagination.createMock()
    	
    	def p1 = Project.addProject("project1")
    	
    	def model = controller.list()
    	
    	assert 1 == model.projects.size()
    	assert model.projects.contains(p1)
    	
    	pagination.verify()
    }
    
	@Test
	public void create() {
		def model = controller.create()
		assert model.project instanceof Project
	}
	
	@Test
	public void save_success() {
	
		def user = User.addUser("alpha")
		
		def springSecurityService = mockFor(grails.plugins.springsecurity.SpringSecurityService)
		springSecurityService.demand.getCurrentUser(1..1) { -> user }
		controller.springSecurityService = springSecurityService.createMock()
		
		controller.params.name = "project1"
		controller.save()	
		
		assert "The project project1 has been created." == controller.flash.message
    	assert "/project/show/1" == response.redirectedUrl
		
		springSecurityService.verify()
	}
    
	@Test
	public void save_fail() {
		
		controller.params.name = ""
		controller.save()	
		
    	assert "/project/create" == view
		assert model.project instanceof Project
	}
	
	@Test
	public void show() {
		def p1 = Project.addProject("project1")
		controller.params.id = p1.id
		def model = controller.show()
		assert p1 == model.project
	}
	
	@Test
	public void edit() {
		def p1 = Project.addProject("project1")
		controller.params.id = p1.id
		def model = controller.edit()
		assert p1 == model.project
	}

	@Test
	public void update_success() {
	
		def p1 = Project.addProject("project1")

		controller.params.id = p1.id
		controller.params.name = "new name"
		
		controller.update()
		
		assert "The project new name has been updated." == flash.message
		assert "/project/show/1" == response.redirectedUrl
	}

	@Test
	public void update_fail() {
	
		def p1 = Project.addProject("project1")

		controller.params.id = p1.id
		controller.params.name = ""
		
		controller.update()
		
    	assert "/project/edit" == view
		assert model.project instanceof Project
	}

	@Test
	public void delete_success() {
		
		def user = User.addUser("alpha")
		
		def springSecurityService = mockFor(grails.plugins.springsecurity.SpringSecurityService)
		springSecurityService.demand.getCurrentUser(1..1) { -> user }
		controller.springSecurityService = springSecurityService.createMock()

		def businessService = mockFor(BusinessService)
		businessService.demand.userCanDelete(1..1) { User u, Project p -> true }
		controller.businessService = businessService.createMock()
		
		def p1 = Project.addProject("project1")
		
		controller.params.id = p1.id
		
		controller.delete()
		
		assert "The project project1 has been deleted." == controller.flash.message
		assert "/project/list" == response.redirectedUrl
		
		businessService.verify()
	}

	@Test
	public void delete_fail() {
		
		def user = User.addUser("alpha")
		
		def springSecurityService = mockFor(grails.plugins.springsecurity.SpringSecurityService)
		springSecurityService.demand.getCurrentUser(1..1) { -> user }
		controller.springSecurityService = springSecurityService.createMock()

		def businessService = mockFor(BusinessService)
		businessService.demand.userCanDelete(1..1) { User u, Project p -> false }
		controller.businessService = businessService.createMock()
		
		def p1 = Project.addProject("project1")
		
		controller.params.id = p1.id
		
		controller.delete()
		
		assert "You cannot delete the project." == controller.flash.message
		assert "/project/show/1" == response.redirectedUrl
		
		businessService.verify()
	}
	
	@Test
	public void withProject_success() {
	
		def p1 = Project.addProject("project1")
		
		controller.params.id = p1.id
		
		def ret = controller.withProject { project ->
			assert project == p1
			"returnvalue"
		}
		
		assert "returnvalue" == ret
		
	}
	
	@Test
	public void withProject_fails() {
	
		def p1 = Project.addProject("project1")
		
		controller.params.id = null
		
		controller.withProject { project ->
			assert false	
		}
		
		assert "The project was not found." == controller.flash.message
		assert "/home/index" == response.redirectedUrl 
		
	}
	
}


