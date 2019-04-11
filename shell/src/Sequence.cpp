#include <iostream>
#include <zconf.h>
#include "Sequence.h"
#include "Pipeline.h"
#include <sys/wait.h>


/**
 * Destructor.
 */
Sequence::~Sequence() {
	for( Pipeline *p : pipelines )
		delete p;
}

/**
 * Executes a sequence, i.e. runs all pipelines and - depending if the ampersand
 * was used - waits for execution to be finished or not.
 */
void Sequence::execute() {
	int counter = 0;
	for( Pipeline *p : pipelines ) {
        int cid = fork();
		if (cid == 0) { // Child proccess should execute the command
			p->execute();
		} 
		else if (cid > 0) { // Parent Process
			if (!p->isAsync()) { // Wait for the child process to finish (unless async)
				waitpid(cid, NULL, 0);
			}
		} else {
			printf("ERROR: pid < 0 in pipeline %d\n", counter);
		}
		counter++;
	}
}
