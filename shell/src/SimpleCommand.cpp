#include <iostream>
#include "SimpleCommand.h"
#include <zconf.h>
#include <memory.h>
#include <fcntl.h>

void SimpleCommand::execute() {
	if(command == "cd") {
        // If no path provided, set path to home
		if (arguments.empty()) { 
			addArgument(getenv("HOME"));
		}
		const char *arg = arguments.front().c_str();

		if (chdir(arg) != 0) {
			perror("ERROR: Changing directory failed");
		} else {
			printf("Changed working directory to: %s", arg);
		}
	} else if(command =="exit") { 
		printf("Program stopping");
		exit(EXIT_SUCCESS);
	} else {
        char *args[arguments.size() +2];

        args[0] = const_cast<char *>(command.c_str());
        args[arguments.size() +1] = nullptr;

        int i = 1;
        for (auto const &arg: arguments) {
            char *c = strdup(arg.c_str());
            std::string argToAdd = arg;
            args[i] = new char[40];
            args[i] = c;
            i++;
        }
        // input output redirection
        for (auto const &red: redirects) {
            printf("REDIRECTING [%d] \n", red.getType());
            // OUTPUT type
            if (red.getType() == red.OUTPUT) {                  

                int fd = open(red.getNewFile().c_str(), O_WRONLY | O_CREAT, 0644);
                if (fd < 0) {
                    perror("ERROR: Couldn't open file to write to\n");
                }

                 // '>'  write to file
                if (red.getOldFileDescriptor() == 1) {         
                    printf("OUTPUT >: %i\n" , red.getOldFileDescriptor());

                    dup2(fd, 1);
                    close(fd);

                // '2>' write error to file
                } else if (red.getOldFileDescriptor() == 2) {   
                    printf("OUTPUT 2>: %i\n", red.getOldFileDescriptor());
                    dup2(fd, 2);
                    close(fd);

                } else {
                    printf("OUTPUT ?: %i\n", red.getOldFileDescriptor());
                }
                    
            // APPEND type
            } else if (red.getType() == red.APPEND) {           
                printf("APPEND >>: %i\n", red.getOldFileDescriptor());
                int fd = open(red.getNewFile().c_str(), O_WRONLY | O_CREAT | O_APPEND, 0644);
                
                if (fd < 0) {
                    perror("ERROR: Couldn't open file to append to\n");
                }

                dup2(fd, 1);
                close(fd);

            // INPUT type
            } else if (red.getType() == red.INPUT) {            
                printf("INPUT <: %i", red.getOldFileDescriptor());
                int fd = open(red.getNewFile().c_str(), O_RDONLY, 0644);
                
                if (fd < 0) {
                    perror("ERROR: Couldn't open file to read from\n");
                }

                dup2(fd, 0);
                close(fd);
            // UNRECOGNIZED type
            } else {
                perror("ERROR: Unrecognized redirection type\n");
            }
        }
        
        printf("%d executing and exiting", getpid());
        execvp(args[0], args);
	}
}



std::string SimpleCommand::toString() {
	return command;
}