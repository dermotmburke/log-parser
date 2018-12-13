# Log Parser

[![Build Status](https://travis-ci.org/dermotmburke/log-parser.svg?branch=master)](https://travis-ci.org/dermotmburke/log-parser)

Running the application.

```
./gradlew bootRun -Pargs="PATH_TO_LOG_FILE"
```
## Solution overview

The solution makes provision for parsing large log files by making use of the GSON streaming parser and using the database as a cache for intermediate log events. When 2 events with the same id are found in the cache, they are used to generate an event which is then stored in the database. The cache is cleared at the end of every run. 

## Future enhancements

### Types

All events are currently being deserialized to a single event class. From the specification it is clear that some log types are "Application events" while others are simply events. With further time, a custom type adaptor could be added to 

### Error handling

For expediency, the solution does not attempt to handle the various errors that could occur. A single invalid log event would most likely cause the whole program to stop processing. With more time, further tests and code could be written to improve the code's error handling capability.
