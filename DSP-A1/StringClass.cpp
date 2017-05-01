#include <iostream>
#include <cstdio>
#include <cstring>
using namespace std;
class YoString {
    friend std::ostream& operator<<(std::ostream& os, const YoString& obj);
    friend std::istream& operator>>(std::istream& is, const YoString& obj);

	public:
		char *string;
		int size;

		YoString() {
			string = NULL;
		}

		YoString(const char *arr) {
			string = (char*) arr;
			size=strlen( (char*) arr);
		}

		YoString(const YoString &q) {
			string = q.string;
			size = q.size;
		}

		void print() {
			std::cout << string << std::endl;
		}

		void printMemoryAddress() {
			std::cout << "Memory address: " << &string << std::endl;
		}

		YoString operator +(const YoString  &q) const {
			YoString s(new char[strlen(string)+strlen(q.string)+1]);
			strcpy(s.string,string);
			strcat(s.string,q.string);
			return s;
		}

};

std::ostream& operator<<(std::ostream& os, const YoString& obj) {
	// write obj to stream
	for (int i = 0 ; i < strlen(obj.string) ; i++)
	    os << obj.string[i];
	return os;
}

std::istream& operator>>(std::istream& is, YoString& obj) {
	// read obj from stream
	/*if( /* YoString could not be constructed )
		is.setstate(std::ios::failbit);*/
	char *tmp = (char*) malloc(1024);
	is >> tmp;
	obj.string = tmp;
	return is;
}

int main() {
	// testing instantiation
	YoString newString("test");
	YoString newStringTwo("testTwo");
	YoString newStringConcat = newString + newStringTwo;
	newString.print();
	newString.printMemoryAddress();
	//newStringConcat.print();
    cout << newStringConcat << endl;
    YoString yourInp("asdfasd");
    cin >> yourInp;
    cout << yourInp << endl;
	return 0;
}
