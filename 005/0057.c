#include <stdio.h>
/*
Multiply and sum all items (array[0] to array[9],
inclusive), of the variable array.
*/
int main(int argc, char const *argv[]) {
  int array1[10];
  for (int i = 0; i<10 ; i++){
    printf("Enter %d number:\n", i+1);
    scanf("%d", &array1[i]);
  }
  int sum=0;
  int product=1;
  for (int i=0;i<10; i++) {
    sum=sum+array1[i];
    product=product*array1[i];
  }
  printf("Suma je %d a product je %d\n", sum, product);
  return 0;
}
