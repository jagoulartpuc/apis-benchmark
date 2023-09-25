# apis-benchmark
This experiment aimed to explore the behavior of the same API implemented in different languages/frameworks, using the K6 tool for performance testing. In total there were 6 APIs in: Bun (JavaScript), Nodejs/Express (JavaScript), Ktor (Kotlin), Spring Webflux (Kotlin), Spring Boot (Kotlin) and Native Go. They all have just two endpoints, a POST /user and /GET user, the test present in the stressIO.js file just executes both endpoints increasing the number of threads over the course of a minute. The test results for each are present in a results.txt file within each folder.
