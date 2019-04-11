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
		
		// When there is only 1 command in the pipeline it should execute immediately
		if (commands.size() == 1) {
			cmd->execute();
			exit(EXIT_SUCCESS);
		} 
		else {
			// Create a new pipe unless we are at the last command
			if (counter != commands.size() - 1) {
				if (pipe(fd) == -1) {
					perror("ERROR: creating pipe failed");
				};
			}

			pid = fork();
			if (pid == 0) { // Current process == child process
				// Read from the read side of the pipe unless its the first command (which should just read from std in)
				if (counter != 0) {
					dup2(storedReadSide, STDIN_FILENO);
					close(storedReadSide);
				}
				// Write to the write side of the pipe unless its the first command (which should just write to std out)
				if (counter != commands.size() -1) {
					close(fd[PIPE_READ]);
					dup2(fd[PIPE_WRITE], STDOUT_FILENO);
					close(fd[PIPE_WRITE]);
				}
				cmd->execute();
				exit(0);
			} else if(pid > 0) { // Current process == parent process
				// Close the readside in the parent proces because it's not needed. 
				// If counter == 0 there is no stored readside yet, so we dont need to close it
				if (counter != 0) {
					close(storedReadSide);
				}
				// Unless we're at the last command we store the readside of the pipe, because the next command in the loop needs it 
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

