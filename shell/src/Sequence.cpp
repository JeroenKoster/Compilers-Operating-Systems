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
	for( Pipeline *p : pipelines ) {
		// FIXME: More code needed?
		// printf("Pipeline %i async: %i\n", counter, p->isAsync());
        int cid = fork();
		if (cid == 0) {
			// printf("%d executing pipeline %d\n", getpid(), counter);
			p->execute();
		} 
		else if (cid > 0) {
			if (!p->isAsync()) {
				// printf("Waiting for %i\n", cid);
				waitpid(cid, NULL, 0);
				// printf("Done waiting\n");
			}
		} else {
			printf("ERROR: pid < 0 in pipeline %d\n", counter);
		}
		counter++;

        // child = 0 exec
        // parent < 0 waitpid op cid van fork, return
	}
	std::cout << getcwd(NULL, NULL) << " -> ";
	std::flush(std::cout);
}
