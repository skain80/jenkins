items = Jenkins.instance.items
for (item in items){
  
  if(item instanceof Job){
    if (! item.getName().contains("SGFXB")){
     item.disabled=true
    }
  }
}
