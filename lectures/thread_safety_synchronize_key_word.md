### Synchronized Keyword and Thread Safety

How thread safety is a thing in multithreading and concurrency development?

1. If multiple thread is visiting the same class, object or method, if this class, object or method can behave as the same as they behave in synchronized execution,
this class, object or method is thread-safe.

2. Thread safety issues are triggered by global variables and static variables.

3. If a thead only has read operation on global or static variables, no write operation, in general, this global or static variable is thread-safe; however, if multiple thread has write opration on this thread, we may consider thread synchronization, otherwise thread safety issue may occur.  
