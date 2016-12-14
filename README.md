# spring-boot-executable-test
In order to switch between application.yml files you must create a file with the same name as the jar with `JAVA_OPTS="-Dspring.profiles.active=<profile>"`. I.E. suppose that you want to use application-qa.yml you need a <jar-name>.conf:
```
JAVA_OPTS="-Dspring.profiles.active=qa"
```

In order to switch between configuration-dev and configuration-qa you need to set the environment variable `AUDITOR_ENVIRONMENT` to dev or qa (the default value is dev).
