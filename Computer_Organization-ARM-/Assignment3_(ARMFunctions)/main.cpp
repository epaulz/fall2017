#include <iostream>

int compute(int a, int b, int *c, int *d){
    return (a * b + *c * *d);
}

int main(){
    int a, b;
    int *c = new int;
    int *d = new int;

    std::cout << "Enter values: ";
    std::cin >> a >> b >> *c >> *d;

    std::cout << a << " " << b << " " << *c << " " << *d << std::endl;

    std::cout << "\nComputing. . .\n\n";
    int result = compute(a, b, c, d);
    std::cout << "Result is " << result << std::endl;

    delete c;
    delete d;

    return 0;
}