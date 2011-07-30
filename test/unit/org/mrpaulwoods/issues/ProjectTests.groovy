package org.mrpaulwoods.issues



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainUnitTestMixin} for usage instructions
 */
@TestFor(Project)
@Mock(Issue)
class ProjectTests {

    @Test
    public void getIssues() {
    
    	def project = new Project(name:"a").save()
    	def issue1 = new Issue(project:project, name:"1", description:"11").save()
    	def issue2 = new Issue(project:project, name:"2", description:"22").save()
    
		def list = project.getIssues()
		
		assert 2 == list.size()
		assert issue1 == list[0]
		assert issue2 == list[1]
    }
}
