package org.mrpaulwoods.issues

class ControllerHelper {

	List<Long> getListOfLongs(def value) {
		if(!value) {
			[]
		} else if(value instanceof String) {
			[Long.valueOf(value)]
		} else if(value instanceof Object[]) {
			value.collect { Long.valueOf(it) }
		} else {
			println value.class
		}
	}


}
