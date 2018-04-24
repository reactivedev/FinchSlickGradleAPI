<h2>Completed Tasks:</h2>

1. Gradle Project with Scala Plugin
2. Basic Model with Slick Mappings
3. Finch Endpoints
4. Twitter Futures to Scala/Slick Translation
4. End to End validation with Twitter Server and H2 Database
5. Reduce clutter by using Repository Pattern, embed generic operations in the Repository.
6. MySQL integration with option to switch between H2/MySQL from Config & remove slick import clutter
7. Automatic deployment to linked docker containers (application & database containers)
8. Add all remaining endpoints, slf4j logging

<h2>Build</h2>
 
    gradle build

<h2>Deployment</h2>

Build, Deploy and Run:

    shart.sh
    
Stop:

    stop.sh
    

<h2>Devils are in Details </h2>

<B>Select Appropriate Configuration (application.conf):</B>
Select appropriate environment in "dev" if running/debugging on host machine, "docker" when deploying
Select appropriate db, "h2" or "mysql"


<B>Docker Notes</B>
When the containers are linked, we reference  by name i.e. mysql url looks like (<b>db</b> is the linked mysql container):


    jdbc:mysql://db:3306/api


mysql:latest uses version 8, which does not uses native passwords, but generates random SHA based passwords BUT NOT when a volume is mounted. So had to revert back to 5.7

<b>Fee DB Operations with Custom Repository Pattern</b>


    private val jobsRepo = new Repository[Job, Jobs](TableQuery[Jobs]) {}
    jobsRepo.all()
    jobsRepo.upsert(job)
    jobsRepo.delete(jobId)


<B>Auto Create DB Tables when missing</B>


    def createSchema(): Unit = {
    
        val tables = Await.result(db.run(MTable.getTables), timeout)
    
        createTable(TableQuery[Candidates])
        createTable(TableQuery[Jobs])
        createTable(TableQuery[Interviews])
    
        def createTable[T <: Table[_]](q: TableQuery[T]): Unit = if(exists(tables, q)) {
          println(s"Table ${q.baseTableRow.tableName} exists")
        } else {
          println(s"Table ${q.baseTableRow.tableName} doesn't exists, will create it.")
          Await.result(create(q), timeout)
        }
      }
    
<h2>Verify</h2>

    docker ps
    docker exec -it <container_id> bash

Then run curl scripts provided.    

<h2>Known issues: </h2>
1. Circe automatic json mapping is not honoring Option Types or Default Values.
    providing custom encoder & decoder might help, but ran into Slick issues.
2. Both H2 & MySql doesn't allow returning entire record on Insert/Update/Upsert operations, only allows returning id (that too auto-incremented Id).

<h2>Future Enhancements:</h2>
1. Authentication & Authorization
2. SCA Support (findbugs)
3. Additional Repository methods (streams, filter by insert/update date range) etc.
4. Logging with Logback


