#include <stdio.h>

/*
Write a progamme which is provided
with three numbers by a user.
1. Find the largest of given 3 numbers.
2. Find the smallest of the given 3
numbers.
3. Find a sum of given 3 numbers.
4. Find an average of given 3 numbers
*/

int main(int argc, char const *argv[]) {
  int a;
  int b;
  int c;
  int sum=0;
  float avr;
  printf("Pls enter 3 numb\n");
  scanf("%d %d %d", &a, &b, &c );

  if (a>b && a>c){
    printf("Largest numb is %d\n", a );
  } else if (b>a && b>c){
    printf("Largest numb is %d\n", b );
  } else {
    printf("Largest numb is %d\n", c );
  }
  if (a<b && a<c){
    printf("smallest numb is %d\n", a );
  } else if (b<a && b<c){
    printf("smallest numb is %d\n", b );
  } else {
    printf("smallest numb is %d\n", c );
  }

  sum=a+b+c;
  avr=sum/3.0;

  printf("Sum is %d\n", sum );
  printf("average is %.2f\n", avr );

  return 0;
}
