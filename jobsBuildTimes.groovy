//println(Jenkins.instance.metaClass.methods.name.sort().unique())
//Jenkins.instance.getJobNames()
//Hudson.instance.items.get(40).lastBuild.time

jobs = ["buildable":[],"nonbuildable":[]]


Date now = new Date()
Date builddate

for (item in Hudson.instance.items)
{
  if(!item.name.contains("Admin")) //omit com.cloudbees.hudson.plugins.folder.Folder
  {
    if(item.buildable)
    {
        //println(item.name + " " + "\"" + item.lastBuild + "\"");
        if(item.lastBuild != null && item.lastBuild.time != "")
        {
            builddate = item.lastBuild.time
          //println(item.name + " " + item.lastBuild.time);

          	jobs["buildable"].add(item.name)
        }
        else
        {
          	println(item.name + " " + "Never Built")
          	jobs["nonbuildable"].add(item.name)
        }
        builddate += 357 //add +1year to last build
      	if(!builddate.after(now))
      	{	
        	println(item.name + " has not been built for 356+ days!")
        }
        
    }
  }
}
