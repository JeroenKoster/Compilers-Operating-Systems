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
	int fd[2];
	int pid;
	int storedReadSide = -1;
	for( SimpleCommand *cmd : commands ) {
		
		if (commands.size() == 1) {
			// if (fork() == 0) {
				cmd->execute();
				exit(1);
			// }
		} 
		else {
			if (counter != commands.size() - 1) {
				pipe(fd);
			}
			pid = fork();
			// std::cout << "Command: " << cmd->toString() << ", Pid: " << pid << ", counter: " << counter << std::endl;
			if (pid == 0) { // Huidge process is child process
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
			} else {
				if (counter != 0) {
					close(storedReadSide);
				} 
				if (counter != commands.size() -1) {
					storedReadSide = fd[PIPE_READ];
					close(fd[PIPE_WRITE]);
				}
			}
		}				
		counter++;
	}
	waitpid(pid, NULL, 0);
	// printf("pipeline finished, exiting %d\n", getpid());
	exit(0);
}

	// if (counter == commands.size() && pid > 0) {
	// 	printf("waiting");
	// 	wait(NULL);
	// 	printf("Parent process finished\n");

		
	// 	// Print a prompt
	// 	std::cout << getcwd(NULL, NULL) << " -> ";
	// 	std::flush(std::cout);

	// }
