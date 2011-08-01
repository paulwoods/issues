
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
    }
    
    private void sample1() {
    
//    	def project = Project.findByName("issuetracker")
//    	if(!project) {
//    		project = new Project(name:"issuetracker")
//    		project.save()
//    	}
    	
//    	def issue1 = Issue.findByProjectAndName(project, "start project")
//    	if(!issue1) {
//    		issue1 = new Issue(project:project, name:"start project", description:"create the initial issue tracker")
//    		issue1.save()
//    	}
    	
    }
    
}
