#include <iostream>
#include <string>
#include <vector>
#include <sstream>

class Box{
protected:
  std::string boxString;
public:
  double length;
  double width;
  double height;
  
  Box(){
    length = 1;
    width = 1;
    height = 1;
  }

  Box(double l, double w, double h){
    length = l;
    width = w;
    height = h;
  }

  operator const char* (){
    std::ostringstream boxStream;

    boxStream << "Box : (" << length << ", " << width << ", " << height << ")";

    boxString = boxStream.str();

    return boxString.c_str();
  }

  Box& operator ++ (){
    length++;
    width++;
    height++;

    return *this;
  }

  Box& operator -- (){
    length--;
    width--;
    height--;

    return *this;
  }

  Box operator + (Box& box2){
    Box result = Box();

    result.length = length + box2.length;
    result.width = width + box2.width;
    result.height = height + box2.height;

    return result;
  }

  void operator = (Box& box2){
    length = box2.length;
    width = box2.width;
    height = box2.height;
  }

  bool operator == (Box& box2){
    return ((length == box2.length) && (width == box2.width) && (height == box2.height));
  }

  bool operator < (Box& box2){
    return ((length < box2.length) && (width < box2.width) && (height < box2.height));
  }

  bool operator <= (Box& box2){
    return ((length <= box2.length) && (width <= box2.width) && (height <= box2.height));
  }

  bool operator > (Box& box2){
    return ((length > box2.length) && (width > box2.width) && (height > box2.height));
  }

  bool operator >= (Box& box2){
    return ((length >= box2.length) && (width >= box2.width) && (height >= box2.height));
  }

  double operator [] (int x){
    if(x == 0){
      return length;
    }else if(x == 1){
      return width;
    }else if(x == 2){
      return height;
    }else{
      return 0;
    }
  }
};

int main() {
  Box box1 = Box(5, 5, 5);
  Box box2 = Box(5, 5, 5);

  ++box1;
  --box2;
  Box box3 = box1 + box2;

  std::cout << box1 << "\n";
  std::cout << box2 << "\n";
  std::cout << box3 << "\n\n";
  std::cout << std::boolalpha;
  std::cout << "Box1 is bigger then Box2 : " << (box1 > box2) << "\n";
  std::cout << "Box1 is bigger then Box3 : " << (box1 > box3) << "\n";
  std::cout << "Box1 is bigger or equal then Box1 : " << (box1 >= box1) << "\n";
  std::cout << "Box1 is equal then Box1 : " << (box1 == box1) << "\n";
  std::cout << "Box3 is smaller then Box1 : " << (box3 < box1) << "\n\n";

  return 0;
}
