def label = 'my-agent-label' // replace with your agent label
def clouds = Jenkins.instance.clouds
def cloudLabels = []
for (cloud in clouds) {
  if (cloud instanceof org.csanchez.jenkins.plugins.kubernetes.KubernetesCloud) {
    def templates = cloud.getTemplates()
    for (template in templates) {
      if (template.getLabel() == label) {
        cloudLabels.add(cloud.getDisplayName())
        break
      }
    }
  }
}
if (cloudLabels.isEmpty()) {
  println("No Kubernetes cloud provides pod templates with label ${label}")
} else {
  println("Kubernetes clouds that provide pod templates with label ${label}: ${cloudLabels.join(', ')}")
}
