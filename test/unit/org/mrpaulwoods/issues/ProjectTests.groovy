package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainUnitTestMixin} for usage instructions
 */
@TestFor(Project)
@Mock([User,Issue,Project,UserRole,UserProject])
class ProjectTests {

//    @Test
//    public void getIssues() {
//    
//    	def project = new Project(name:"a").save()
//    	def issue1 = new Issue(project:project, name:"1", description:"11").save()
//    	def issue2 = new Issue(project:project, name:"2", description:"22").save()
//    
//		def list = project.getIssues()
//		
//		assert 2 == list.size()
//		assert issue1 == list[0]
//		assert issue2 == list[1]
//    }
    
    @Test
    public void addProject() {
    	assert 0 == Project.count()
    	def p1 = Project.addProject("project1")
    	assert 1 == Project.count()
    }
    
    @Test
    public void addProject_duplicate() {
    	assert 0 == Project.count()
    	def p1 = Project.addProject("project1")
    	def p2 = Project.addProject("project1")
    	assert 1 == Project.count()
    	assert p1 == p2
    }
    
    @Test
    public void addUser() {
    	def p1 = Project.addProject("project1")
    	def user = User.addUser("alpha")
    	p1.addUser user, UserProject.Access.Admin
    	
		assert 1 == UserProject.count()
    }
    
     
    
    
}
