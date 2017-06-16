#include <iostream>
using namespace std;

int charToInt(char* inp) {
	int out = 0;
	for(int i = 0; inp[i] != '\0'; ++i)
		out = out*10 + inp[i] - '0';
	return out;
}

int main() {
	string inp;
	char str[10000];
	cin >> str;
	cout << charToInt(str) << endl;
	int s1 = 3;
	int s2 = 5;
	int *h1 = new int;
	int *h2 = new int;	
	*h1 = 4;
	*h2 = 6;
	cout << "Stack 1: " << &s1 << endl;
	cout << "Stack 2: " << &s2 << endl;
	cout << "Heap 1: " << &h1 << endl;
	cout << "Heap 2: " << &h2 << endl;
	cout << "charToInt: " << &charToInt << endl;
	return 0;
}
