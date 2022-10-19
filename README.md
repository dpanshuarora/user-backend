user-backend application is used to store and retrieve user data.

## IDEA setup

- Install the Lombok Plugin by going to Preferences -> Plugins and search by lombok.
- Check the "Enable annotation processing" checkbox under Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors


## Build:

The command below will trigger unit, integration tests besides compiling the jar for the app:

```
./gradlew clean build
```

### Running the app and its dependencies

Run the main class from you IDE, or from the command line:

```
./gradlew bootRun
```
