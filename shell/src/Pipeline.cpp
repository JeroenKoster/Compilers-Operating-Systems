#include <iostream>
#include "Pipeline.h"
#include "SimpleCommand.h"
#include <zconf.h>
#include <wait.h>

/**
 * Destructor.
 */
Pipeline::~Pipeline() {
	for( SimpleCommand *cmd : commands )
		delete cmd;
}

/**
 * Executes the commands on this pipeline.
 */
void Pipeline::execute() {
	int counter = 0;
	int fd[2];
	int pid;
	int storedReadSide = -1;
	for( SimpleCommand *cmd : commands ) {
		
		if (commands.size() == 1) {
			cmd->execute();
			exit(EXIT_SUCCESS);
		} 
		else {
			if (counter != commands.size() - 1) {
				if (pipe(fd) == -1) {
					perror("ERROR: creating pipe failed");
				};
			}
			pid = fork();
			if (pid == 0) { // Current process == child process
				if (counter != 0) {
					dup2(storedReadSide, STDIN_FILENO);
					close(storedReadSide);
				}
				if (counter != commands.size() -1) {
					close(fd[PIPE_READ]);
					dup2(fd[PIPE_WRITE], STDOUT_FILENO);
					close(fd[PIPE_WRITE]);
				}
				cmd->execute();
				exit(0);
			} else if(pid > 0) { // Current process == parent process
				if (counter != 0) {
					close(storedReadSide);
				} 
				if (counter != commands.size() -1) {
					storedReadSide = fd[PIPE_READ];
					close(fd[PIPE_WRITE]);
				}
			} else {
				perror("ERROR: Invalid pid after forking");
			}
		}				
		counter++;
	}
	waitpid(pid, NULL, 0);
	exit(EXIT_SUCCESS);
}

