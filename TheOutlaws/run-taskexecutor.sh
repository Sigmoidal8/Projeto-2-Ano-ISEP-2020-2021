#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=base.daemon.automatictaskexecutor/target/base.daemon.automatictaskexecutor-1.3.0-SNAPSHOT.jar:base.daemon.automatictaskexecutor/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP TaskExecutorDaemon
