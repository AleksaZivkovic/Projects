#include <stdio.h>
#include <stdlib.h>

/*
-------------
magicni broj
------------
Unesi broj i ispisi da li je broj magican
Magican broj je broj kojem je suma dijelioca jednaka njemu neukljucujuci taj broj
(validacija unos : broj mora da bude pozitivan tj n > 0)
*/

int main() {
  int n;
  int sum=0;
  do{
    printf("Unesi broj\n");
    scanf("%d", &n);
  }while (n<1);

  for (int i=1;i<n;i++){
    if (n%i==0){
      sum=sum+i;
    }
  }

  if(sum==n){
    printf("Magican broj\n" );
  }else{
    printf("Nije\n");
  }

  return 0;
}
