# How to run postgres on your MacBook in the docker container

This README file will guild you to run postgres on your MacBook in the docker container. 

### Step 1: Use the Docker Engine
In the terminal type the following command to install Docker Engine.  If you haven't updated your Homebrew, please run 
the **brew update** command twice.  

```
brew update
brew update
brew cask install docker
brew install colima
colima start
```

### Step 2: Start/Shutdown the docker postgres
In the terminal type the following batch command to start the docker postgres. 
You **MUST** start and shutdown the docker postgres from the docker folder.
```
cd docker
./startup.sh
or
./shutdown.sh
```

