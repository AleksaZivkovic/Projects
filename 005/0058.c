#include <stdio.h>
#include <string.h>
/*
Ask a user to enter a string. (use gets() function)
and check whether a string is a palindrome.
*/
int main(int argc, char const *argv[]) {
  char a[100];
  char b[100];

  printf("Unesi string:\n");
  gets (a);
  strcpy(b,a);
  strrev(b);

  if (strcmp(a,b)==0){
    printf("Palindrom!\n");
  }else {
    printf("Nije palindrom!\n");
  }
  return 0;
}
