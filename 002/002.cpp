#include <iostream>
#include <string>
#include <vector>
#include "SM.h"

int main(){
	SM sm;
	std::vector<std::string> v;

	std::string sentence = "Some random sentence ";
	std::string sentence2 = "to   test  the code!!!";

	sm.split(sentence, ' ', v);
	sm.split(sentence2, ' ', v);

	std::cout << sm.vectorToString(v) << "\n";
	return 0;
}
