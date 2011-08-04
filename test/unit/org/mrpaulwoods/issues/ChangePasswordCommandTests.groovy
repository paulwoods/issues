package org.mrpaulwoods.issues

import grails.test.mixin.*
import org.junit.*

class ChangePasswordCommandTests {
	
    @Test
    public void matchingPasswordsValidate() {
		def cmp = new grails.util.ClosureToMapPopulator()
		def map = cmp.populate(ChangePasswordCommand.constraints)
		
		def obj = [ user: [ password : "abc", springSecurityService : [ encodePassword: { p -> "abc" } ] ] ]
		
		assert true  == map.password.validator.call("x", obj)
    }
    
	
    @Test
    public void mismatchPasswordsDoNotValidate() {
		def cmp = new grails.util.ClosureToMapPopulator()
		def map = cmp.populate(ChangePasswordCommand.constraints)
		
		def obj = [ user: [ password : "abc", springSecurityService : [ encodePassword: { p -> "123" } ] ] ]
		
		assert false == map.password.validator.call("x", obj)
    }
    
}

