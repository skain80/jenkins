oneyr = new Date() - 356

items = Jenkins.instance.items
for (item in items){
  
  if(item instanceof Job){
    
  	if (item.lastBuild == null){
    	println(item.name + " never built")
  	}
	else if(!item.disabled && \
        (item.lastBuild.time < oneyr)){
 		println(item.name + ": " + item.lastBuild.time) 
	}
  }
}
