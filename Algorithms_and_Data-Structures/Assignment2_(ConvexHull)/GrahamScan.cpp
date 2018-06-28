/*
    Eric Paulz
    CPSC 2120
    Assignment 2: Graham's Scan & Convex Hulls
*/

#include <vector>
#include <cmath>
using namespace std;
#include "GrahamScan.h"

// returns bearing of line from Point A to Point B
double angle(Point &A, Point &B){
    const double TWO_PI = 6.2831853071795865;
    const double RAD2DEG = 57.2957795130823209;

    if(A.getX() == B.getX() && A.getY() == B.getY())
        return 0.0;

    double theta = atan2(B.getX() - A.getX(), A.getY() - B.getY());

    if(theta < 0.0)
        theta += TWO_PI;
    
    return RAD2DEG * theta;
}

// sorts points by angle counterclockwise from list[0] using insertion sort
void sortByAngleFromFirst(vector<Point> &list)
{
   for(unsigned int round=1; round<list.size(); round++) {
      int i=round; 
      Point newPoint = list[round];
      double newAngle = angle(list[0], newPoint );
      while( i>1 && ( newAngle < angle(list[0], list[i-1]) ) ) {
	     list[i]=list[i-1];
         i--;
      }
      list[i] = newPoint;
   }
} 

// finds leftmost point in list and moves it into position 0
void moveLeftmostFirst(vector<Point> &list){
    int index = 0;
    Point temp = list.at(0);

    for(size_t i = 1; i < list.size(); i++){
        if(list.at(i).getX() < temp.getX()){
            temp = list.at(i);
            index = i;
        }
    }

    auto it = list.begin();
    it = list.insert(it, temp);
    list.erase(list.begin()+(index+1));
}

// returns true if going A to B to C is a left turn
bool isLeftTurn( Point &A, Point &B, Point &C ) 
{
      return (B.getY()-A.getY())*(C.getX()-B.getX()) <
                    (C.getY()-B.getY())*(B.getX()-A.getX());
}

// prints out contents of list
void debugDump(vector<Point> &list){
    for(size_t i = 0; i < list.size(); i++){
        cout << list.at(i) << " ";
    }
    cout << endl;
}

// determines convex hull of list using Graham scan
vector<Point> convexHull(vector<Point> &list){
    PointStack *stack = new PointStack();
    vector<Point> newList;

    if(list.empty())
        return newList;
    else if(list.size() == 1)
        newList.push_back(list.at(0));
    else{
        stack->push(list.at(0));
        stack->push(list.at(1));
        
        size_t i = 2;
        while(i < list.size()){
            if(isLeftTurn(stack->peekUnder(), stack->peek(), list.at(i))){
                stack->push(list.at(i));
                i++;
            }
            else{
                stack->pop();
            }
        }
        while(!stack->isEmpty()){
            Point temp = stack->pop();
            newList.push_back(temp);
        }
    }

    return newList;
}

// determines convex shells of list by repeated use of convexHull
void shellIt(vector<Point> &list){
    while(!list.empty()){
        moveLeftmostFirst(list);
        sortByAngleFromFirst(list);
        
        vector<Point> shells(convexHull(list));
        for(size_t i = 0; i < shells.size(); i++){
            for(size_t j = 0; j < list.size(); j++){
                if(list.at(j) == shells.at(i))
                    list.erase(list.begin()+j);
            }
        }
        debugDump(shells);
        shells.clear();
    }
}
