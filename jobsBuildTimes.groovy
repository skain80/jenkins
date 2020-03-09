//println(Jenkins.instance.metaClass.methods.name.sort().unique())
//Jenkins.instance.getJobNames()
//Hudson.instance.items.get(40).lastBuild.time

jobs = ["buildable":[],"nonbuildable":[]]


Date now = new Date()
Date builddate

for (item in Hudson.instance.items) {
  
  if(!item.name.contains("Admin")) { //omit com.cloudbees.hudson.plugins.folder.Folder
    
    if(item.buildable) {
     	if(item.lastBuild != null && item.lastBuild.time != "") {
			builddate = item.lastBuild.time
        }
        else {
          	println(item.name + ",Never," + Hudson.instance.rootUrl + item.url)
          	continue
        }
      
      	days_since_last_build = now - builddate

      
      	if(days_since_last_build >= 356) {
        	println(item.name + ","+days_since_last_build+"," + Hudson.instance.rootUrl + item.url)
        }
    }
  }
}
