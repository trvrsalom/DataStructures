// If-asg2: Find the errors worksheet
//        original work by Joe

#include <iostream>
using namespace std;

int main()
{
    cout << "********************************************************" << endl;
    cout << "********************************************************" << endl;
    cout << "Pound/Dollar Money Converter";
    cout << "********************************************************"<< endl;
    cout << "********************************************************"<< endl;

    cout << "Is this the Appropriate Transaction:Dollars->Pounds "<< endl;
    cout << "Enter YES or No to continue: "<< endl;
    string response;
    cin >> response;

    if( response == "YES" )
    {
    cout << "Enter the amount of Dollars here: "<< endl;
    double dollars = 0;
    cin >> dollars;

    double pounds1= 0.6008124544;
    double transaction1= dollars*pounds1;
    //Transaction 1

    cout << "For your "<<dollars<< " Dollars, You will recieve:" << endl;
    cout << transaction1<< " Pounds"<< endl;
    }
    else
    {
    cout << "Enter the amount of Pounds here: "<< endl;
    double pounds =0;
    cin >> pounds;

    //Transaction 2
    double dollars1=1.4;
    double transaction2= pounds/dollars1;

    cout<< "For your "<<pounds<< " Pounds, You will recieve:"<< endl;
    cout<< transaction2<< " Dollars"<< endl;
}
    return 0;
}
