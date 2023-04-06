import java.time.LocalDate
import java.time.Period
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

def startDate = LocalDate.now().minusYears(1)
def endDate = LocalDate.now()
def formatter = DateTimeFormatter.ofPattern("yyyy-MM")

def pipelineCounts = [:]

startDate.withDayOfMonth(1).datesUntil(endDate.withDayOfMonth(1).plusMonths(1), Period.ofMonths(1)).each { month ->
  def monthStr = month.format(formatter)
  def count = Jenkins.instance.getAllItems(org.jenkinsci.plugins.workflow.job.WorkflowJob.class).findAll { job ->
    def firstBuild = job.getFirstBuild()
    firstBuild && LocalDate.ofInstant(Instant.ofEpochMilli(firstBuild.getTimeInMillis()), ZoneId.systemDefault()).withDayOfMonth(1) == month.withDayOfMonth(1)
  }.size()
  pipelineCounts[monthStr] = count
}

pipelineCounts.each { month, count ->
  println "${month}: ${count} pipelines"
}
