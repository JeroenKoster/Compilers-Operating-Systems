#include <iostream>
#include "SimpleCommand.h"
#include <zconf.h>
#include <memory.h>
#include <fcntl.h>

void SimpleCommand::execute() {
	std::cout << "Executing " << this->toString() << std::endl;
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

        for (auto const &red: redirects) {
            int fd = open(red.getNewFile().c_str(), O_WRONLY | O_CREAT, 0644);
            if (fd < 0) throw std::invalid_argument("open error");

            dup2(fd, 1); // send output to new 'file' and close stdout
            close(fd); // close file
        }

        execvp(args[0], args);

        //Execute programs
        // if no path: execute program in current working directory by ./
        // if path: execute program from PATH variable (contains directories such as /usr/bin)

	}

	// Just for debugging
	// int counter = 0;
	// std::cout << "ARGUMENTS: " << std::endl;
	// for(auto const& arg: arguments) {
	// 	std::cout << "  arg[" << counter << "]: " << arg << std::endl;
	// 	counter++;
	// }
}



std::string SimpleCommand::toString() {
	return command;
}