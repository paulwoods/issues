package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(Project)
@Mock([User,Issue,Project,UserRole,UserProject])
class ProjectTests {

    @Test
    public void addProject() {
    	assert 0 == Project.count()
    	def p1 = Project.addProject("project1")
    	assert 1 == Project.count()
    	assert "project1" == Project.list()[0].name
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
		assert user == UserProject.list()[0].user
		assert p1 == UserProject.list()[0].project
    }
    
    @Test
    public void getUsers() {
    
    	def a = User.addUser("alpha")
    	def b = User.addUser("beta")
    	def c = User.addUser("charlie")
    	
    	def p1 = Project.addProject("p1")
    	def p2 = Project.addProject("p2")
    	
    	p1.addUser a, UserProject.Access.Admin    	
    	p1.addUser b, UserProject.Access.Read
    	p2.addUser c, UserProject.Access.Edit    	
    	
    	assert 2 == p1.users.size()
    	assert true == p1.users.contains(a)
    	assert true == p1.users.contains(b)

    	assert 1 == p2.users.size()
    	assert true == p2.users.contains(c)
    }
    
    @Test
    public void userWithNoneAccessNotInUserList() {
		def a = User.addUser("alpha")
		def b = User.addUser("beta")
		def p1 = Project.addProject("p1")

		p1.addUser a, UserProject.Access.None    	
		p1.addUser b, UserProject.Access.Admin

		assert 1 == p1.users.size()
		assert true == p1.users.contains(b)
	}
	
}
