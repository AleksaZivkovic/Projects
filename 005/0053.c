#include <stdio.h>

/*
Write a program which has student‘s grades
stored in an array. Enter number of grades n and then enter n
grades in an array and calculate the average.
*/

int main(int argc, char const *argv[]) {
  int n;
  printf("Enter number of grades\n");
  scanf("%d", &n);
  int ar[n];

  for (int i=0; i<n; i++){
    printf("Enter %d grade:\n", i+1 );
    scanf("%d",&ar[i]);
  }
  int sum=0;
  for (int i=0; i<n; i++){
    sum=sum + ar[i];
  }
  float average= sum / (float)n;

  printf("The average of your grades is %.2f\n", average );

  return 0;
}
