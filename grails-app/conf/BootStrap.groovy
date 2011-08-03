
import org.mrpaulwoods.issues.*

class BootStrap {
	
    def init = { servletContext ->
    	security()
    	sample1()
    }
    
    def destroy = {
    }
    
    def security() {
    	Role.addRole("ROLE_ADMIN")
    	Role.addRole("ROLE_USER")
    	
    	def paulwoods = User.addUser("paulwoods")
    	UserRole.addUserRole paulwoods, Role.admin
    	
    	def admin = User.addUser("admin")
    	UserRole.addUserRole admin, Role.user
    	
    	def edit = User.addUser("edit")
    	UserRole.addUserRole edit, Role.user
    	
    	def read = User.addUser("read")
    	UserRole.addUserRole read, Role.user
    	
    	def none = User.addUser("none")
    	UserRole.addUserRole none, Role.user
    }
    
    private void sample1() {
    	def project = Project.addProject("Issue Tracker")
    	project.addUser User.findByUsername("admin"), UserProject.Access.Admin
    	project.addUser User.findByUsername("edit"), UserProject.Access.Edit
    	project.addUser User.findByUsername("read"), UserProject.Access.Read
    	project.addUser User.findByUsername("none"), UserProject.Access.None
    	
    	
    }
    
}
