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
	// std::cout << "Pipeline::execute()" << std::endl;
	int counter = 0;
	int pipefd[2];
	int pid;
	int READ_SIDE = 0;
	for( SimpleCommand *cmd : commands ) {
		
		if (commands.size() == 1) {
			cmd->execute();
		} else {
			READ_SIDE = pipefd[PIPE_READ];
			pipe(pipefd);
			pid = fork();
			std::cout << "Command: " << cmd->toString() << ", Pid: " << pid << ", counter: " << counter << std::endl;
			if (pid == 0) { // Huidge process is child process
				if (counter == 0) { // Eerste command, redirect output naar WRITE side
					std::cout << cmd->toString() << ": redirecting stdout" << std::endl;
					dup2(pipefd[PIPE_WRITE], STDOUT_FILENO);
					close(pipefd[PIPE_WRITE]);
				} else if(counter == (commands.size() - 1)) { // Laatste command, redirect input naar READ side
					std::cout << cmd->toString() << ": redirecting stdin" << std::endl;
					dup2(READ_SIDE, STDIN_FILENO);
					close(READ_SIDE);
				} else {
					dup2(READ_SIDE, STDIN_FILENO);
					dup2(pipefd[PIPE_WRITE], STDOUT_FILENO);
					// close(READ_SIDE);
					close(pipefd[PIPE_WRITE]);
				}
				cmd->execute();
			} else if (pid < 0) { 
				std::cerr << "Error, PID = " << pid << " Command = " << cmd->toString() << std::endl;
			} else { // Huidige process is parent process, doe niets
				// close(pipefd[PIPE_WRITE]);
				// close(pipefd[PIPE_READ]);
				printf("Parent process waiting for pid %d\n", pid);
				if(counter == commands.size() - 1 && waitpid(pid, 0, 0) < 0) {
					std::cout << "Done waiting" << std::endl;
					return;
				}
			}
			counter++;
			
		}
	}
}