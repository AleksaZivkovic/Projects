#include <stdio.h>

int main(){
	int a;

	printf("Input 3 digit number: ");
	scanf("%d", &a);

	while(a < 100 || a > 999){
		printf("Input 3 digit number: ");
		scanf("%d", &a);
	}
	printf("Middle digit of %d", a);

	a /= 10;
	int b = a % 10;

	printf(" is %d\n", b);
	return 0;
}
