#include <stdio.h>

int main(){
	int a, b, c;

	printf("Input side A of triangle: ");
	scanf("%d", &a);
	printf("Input side B of triangle: ");
        scanf("%d", &b);
        printf("Input side C of triangle: ");
        scanf("%d", &c);

	int p = a + b + c;
	printf("Total perimeter of that triangle is %d\n", p);

	return 0;
}
