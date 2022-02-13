#include <stdio.h>

/*
Enter 10 numbers in an array and print out the
eighth number.
*/

int main(int argc, char const *argv[]) {
    int ar[10];

    for(int i = 0; i < 10; i++){
      printf("Enter %d number:\n", i+1);
      scanf("%d", &ar[i] );
    }
    printf("Eighth element of your array is: %d\n", ar[7] );
  return 0;
}
