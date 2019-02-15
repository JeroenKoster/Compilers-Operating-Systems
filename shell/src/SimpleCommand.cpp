#include <iostream>
#include "SimpleCommand.h"
#include <zconf.h>

void SimpleCommand::execute() {
	if(command == "cd") {
		if (arguments.empty()) { // If no path provided, set path to home
			addArgument(getenv("HOME"));
		}
		const char *arg = arguments.front().c_str();

		if (chdir(arg) != 0) {
			std::cerr << "Error while changing dir" << std::endl;	
		} else {
			std::cout << "Changed working directory to: " << arg << std::endl;
		}


	} else if(command =="exit") { // Currently exits in main.cpp prior to reaching this
		std::cout << "Program stopping" << std::endl;
		exit(0);
	} else {
		std::cout << "Command not recognized" << std::endl;
	}

	// Just for debugging
	int counter = 0;
	std::cout << "ARGUMENTS: " << std::endl;
	for(auto const& arg: arguments) {
		std::cout << "  arg[" << counter << "]: " << arg << std::endl;
		counter++;
	}
}