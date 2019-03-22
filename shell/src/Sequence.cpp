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
	// std::cout << "FIXME: You should change Sequence::execute()" << std::endl;

	int counter = 0;
	printf("Count: %i\n", pipelines.size());
	for( Pipeline *p : pipelines ) {
		// FIXME: More code needed?
		printf("Pipeline %i async: %i\n", counter, p->isAsync());
        int cid = fork();
		counter++;
		if (cid == 0) {
			p->execute();
		} 
		else if (cid > 0 && !p->isAsync()) {
			printf("Waiting for %i\n", cid);
			waitpid(cid,0,0);
			printf("Done waiting");
		}
        // child = 0 exec
        // parent < 0 waitpid op cid van fork, return
		
	}
}
