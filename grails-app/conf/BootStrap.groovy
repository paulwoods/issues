
import org.mrpaulwoods.issues.*

class BootStrap {
	
    def init = { servletContext ->
    	security()
    	sample1()
    }
    
    def destroy = {
    }
    
    def security() {
    	def admin = Role.addRole("ROLE_ADMIN")
    	def user = Role.addRole("ROLE_USER")
    	
    	def paulwoods = User.addUser("paulwoods")
    	UserRole.addUserRole paulwoods, admin
    	
    	def read = User.addUser("read")
    	UserRole.addUserRole read, user
    	
    	def edit = User.addUser("edit")
    	UserRole.addUserRole edit, user
    	
    	def none = User.addUser("none")
    	UserRole.addUserRole none, user
    }
    
    private void sample1() {
    	def project = Project.addProject("issuetracker")
    	project.addUser User.findByUsername("paulwoods"), UserProject.Access.Admin
    	project.addUser User.findByUsername("read"), UserProject.Access.Read
    	project.addUser User.findByUsername("edit"), UserProject.Access.Edit
    	project.addUser User.findByUsername("none"), UserProject.Access.None
    }
    
}
