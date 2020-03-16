jobs = []
jobs_never_built = []
rooturl = " "+Jenkins.instance.rootUrl
oneyr = new Date() - 365

items = Jenkins.instance.items
for (item in items){
  
  if(item instanceof Job){
    
  	if (item.lastBuild == null){
      		jobs_never_built.add(item)
  	}
	else if(!item.disabled && \
        (item.lastBuild.time < oneyr)){
      		jobs.add(item)
	}
  }
}

for (j in jobs){
 println(j.lastBuild.time.toString() + rooturl + j.url) 
}
for (j in jobs_never_built){
 println(j.lastBuild + rooturl + j.url) 
}

