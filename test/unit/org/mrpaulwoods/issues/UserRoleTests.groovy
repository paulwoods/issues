package org.mrpaulwoods.issues



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainUnitTestMixin} for usage instructions
 */
@TestFor(UserRole)
@Mock([User,Role])
class UserRoleTests {
	
	def user
	def role
	
	@Before
	public void setup() {
		User.metaClass.static.encodePassword = { -> }

		user = User.addUser("alpha")
		role = Role.addRole("ROLE")
	}
	
	@After
	public void tearDown() {
		GroovySystem.metaClassRegistry.removeMetaClass User.class
	}
	
    @Test
    public void addUserRole_new() {
		assert 0 == UserRole.count()
		UserRole.addUserRole(user, role)
		assert 1 == UserRole.count()
    }

    @Test
    public void addUserRoleRole_exists() {
		def ur1 = UserRole.addUserRole(user, role)
		def ur2 = UserRole.addUserRole(user, role)
		assert 1 == UserRole.count()
		assert ur2 == ur1
    }
    
}
