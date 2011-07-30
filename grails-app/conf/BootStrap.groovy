
import org.mrpaulwoods.issues.*

class BootStrap {

    def init = { servletContext ->
    	sample1()
    }
    
    def destroy = {
    }
    
    
    private void sample1() {
    
    	def project = Project.findByName("issuetracker")
    	if(!project) {
    		project = new Project(name:"issuetracker")
    		project.save()
    	}
    	
    	def issue1 = Issue.findByProjectAndName(project, "start project")
    	if(!issue1) {
    		issue1 = new Issue(project:project, name:"start project", description:"create the initial issue tracker")
    		issue1.save()
    	}
    	
    }
    
}
