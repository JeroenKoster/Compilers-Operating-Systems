#include <iostream>
#include "SimpleCommand.h"

void SimpleCommand::execute() {
	std::cout << "FIXME: You should change SimpleCommand::execute()" << std::endl;
	// FIXME: Your code here...

	if(command == "cd") {
		std::cout << "CD ENTERED" << std::endl;
	} else if(command =="exit") { // Currently exits in main.cpp prior to reaching this
		std::cout << "Exit ENTERED" << std::endl;
	} else {
		std::cout << "Command not recognized" << std::endl;
	}
	int counter = 0;
	for(auto const& arg: arguments) {
		std::cout << "arg[" << counter << "]: " << arg << std::endl;
		counter++;
	}
}