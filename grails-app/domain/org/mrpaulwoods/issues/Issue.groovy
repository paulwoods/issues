package org.mrpaulwoods.issues

import groovy.transform.ToString
	
@ToString
class Issue {
	
	Project project
	String name
	String description
	
    static mapping = {
    	sort "name"
    }
    
    static constraints = {
    	name nullable:false, blank:false, unique:"project"
    	description nullable:true, blank:false, maxSize:1024
    }
    
}
