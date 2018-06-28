#include <stdio.h>

int compute(int a, int b, int *c, int *d){
    return (a * b + *c * *d);
}

int main(){
	int a, b, c, d;
	int *cPtr = &c;
	int *dPtr = &d;

	scanf("%d %d %d %d", &a, &b, cPtr, dPtr);
	int result = compute(a, b, cPtr, dPtr);
	printf("\n%d*%d + %d*%d = %d\n\n", a, b, c, d, result);

	return 0;
}
