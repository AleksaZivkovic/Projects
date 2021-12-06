#include <stdio.h>

int main(){
	int a, b, c;

	printf("Input first number: ");
	scanf("%d", &a);
        printf("Input second number: ");
        scanf("%d", &b);
        printf("Input third number: ");
        scanf("%d", &c);

	if(a < b){
		if(a < c){
			if(c < b){
				printf("%d %d %d", a, c, b);
			}else{
				printf("%d %d %d", a, b, c);
			}
		}else{
			printf("%d %d %d", c, a, b);
		}
	}else{
		if(b < c){
			if(c < a){
				printf("%d %d %d", b, c, a);
			}else{
				printf("%d %d %d", b, a, c);
			}
		}else{
			printf("%d %d %d", c, b, a);
		}
	}
	printf("\n");

	return 0;
}
