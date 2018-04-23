Completed Tasks:

1. Gradle Project with Scala Plugin
2. Basic Model with Slick Mappings
3. Finch Endpoints
4. Twitter Futures to Scala/Slick Translation
4. End to End validation with Twitter Server and H2 Database
5. Reduce clutter by using Repository Pattern, embed generic operations in the Repository.
6. MySQL integration with option to switch between H2/MySQL from Config & remove slick import clutter
7. Automatic deployment to linked docker containers (application & database containers)
8. Add all remaining endpoints, slf4j logging, metrics

Known issues:
1. Circe automatic json mapping is not honoring Option Types or Default Values.
    TODO: providing custom encoder & decoder might help, but ran into Slick issues.
2. Both H2 & MySql doesn't allow returning entire record on Insert/Update/Upsert operations, only allows returning id (that too auto-incremented Id).

Future Enhancements:
1. Authentication & Authorization
2. SCA Support (findbugs)
3. Additional Repository methods (streams, filter by insert/update date range) etc.
4. Logging with Logback

