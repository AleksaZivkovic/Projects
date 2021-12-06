#include <stdio.h>

int main(){
	int a = 0;

	do{
		printf("Input 4 digit number: ");
		scanf("%d", &a);
	}while(a < 1000 || a > 9999);

	printf("%d", a);
	int digit4 = a % 10;
	a /= 10;

	int digit3 = a % 10;
	a /= 10;

	int digit2 = a % 10;
	a /= 10;

	int digit1 = a;

	if(digit1 == digit4 && digit2 == digit3){
		printf(" is palindrome number\n");
	}else{
		printf(" is not palindrome number\n");
	}

	return 0;
}
