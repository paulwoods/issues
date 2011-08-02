package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

@TestFor(BusinessService)
@Mock([User,Project,UserProject,Issue,Role,UserRole])
class BusinessServiceTests {
	
	def service
	
	User system
	User admin
	User edit
	User read
	User none
	User unknown
	
	Role roleAdmin
	Role roleUser
	
	Project project1
	
	@Before
	public void setup() {
		service = new BusinessService()
		
		system = User.addUser("system")
		admin = User.addUser("admin")
		edit = User.addUser("edit")
		read = User.addUser("read")
		none = User.addUser("none")
		unknown = User.addUser("unknown")	// has no UserProject to the project
		
		roleAdmin = Role.addRole("ROLE_ADMIN")
		roleUser = Role.addRole("ROLE_USER")
		
		new UserRole(user:system, role:roleAdmin).save()
		new UserRole(user:admin, role:roleUser).save()
		new UserRole(user:edit, role:roleUser).save()
		new UserRole(user:read, role:roleUser).save()
		new UserRole(user:none, role:roleUser).save()

		project1 = Project.addProject("project1")
		project1.addUser admin, UserProject.Access.Admin
		project1.addUser edit, UserProject.Access.Edit
		project1.addUser read, UserProject.Access.Read
		project1.addUser none, UserProject.Access.None
	}
	
	@Test
	public void isSystemAdmin() {
		assert true == service.isSystemAdmin(system)
		assert false == service.isSystemAdmin(admin)
		assert false == service.isSystemAdmin(edit)
		assert false == service.isSystemAdmin(read)
		assert false == service.isSystemAdmin(none)
		assert false == service.isSystemAdmin(unknown)
	}
	
	@Test
	public void isProjectAdmin() {
		assert false == service.isProjectAdmin(system, project1)
		assert true == service.isProjectAdmin(admin, project1)
		assert false == service.isProjectAdmin(edit, project1)
		assert false == service.isProjectAdmin(read, project1)
		assert false == service.isProjectAdmin(none, project1)
		assert false == service.isProjectAdmin(unknown, project1)
	}
	
	@Test
	public void systemCanDelete() {
		assert true == service.userCanDelete(system, project1)
	}

	@Test
	public void adminCanDelete() {
		assert true == service.userCanDelete(admin, project1)
	}
	
	@Test
	public void editCanNotDelete() {
		assert false == service.userCanDelete(edit, project1)
	}
	
	@Test
	public void readCanNotDelete() {
		assert false == service.userCanDelete(read, project1)
	}
	
	@Test
	public void noneCanNotDelete() {
		assert false == service.userCanDelete(none, project1)
	}
	
	@Test
	public void unknownCanNotDelete() {
		assert false == service.userCanDelete(unknown, project1)
	}
	

}
