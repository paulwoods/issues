package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(BusinessService)
@Mock([User,Project,UserProject,Issue])
class BusinessServiceTests {
	
	def service
	
	@Before
	public void setup() {
		service = new BusinessService()
	}
	
	@Test
	public void canDelete() {
		def a = User.addUser("admin")
		def e = User.addUser("edit")
		def r = User.addUser("read")
		def n = User.addUser("none")
		def x = User.addUser("unknown")
		
		def p1 = Project.addProject("project1")
		p1.addUser a, UserProject.Access.Admin
		p1.addUser e, UserProject.Access.Edit
		p1.addUser r, UserProject.Access.Read
		p1.addUser n, UserProject.Access.None
		
		assert true == service.userCanDelete(a, p1)
		assert false == service.userCanDelete(e, p1)
		assert false == service.userCanDelete(r, p1)
		assert false == service.userCanDelete(n, p1)
		assert false == service.userCanDelete(x, p1)
		
	}

}
