#include <stdio.h>

int main(){
	int a;

	printf("Input number: ");
	scanf("%d", &a);

	int b = a / 2;
	b *= 2;

	if(a == b){
		printf("%d is even\n", a);
	}else{
		printf("%d is odd\n", a);
	}

	return 1;
}
