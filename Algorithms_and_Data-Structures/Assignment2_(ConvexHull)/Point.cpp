/*
    Eric Paulz
    CPSC 2120
    Assignment 2: Graham's Scan & Convex Hulls
*/

#include "Point.h"

// contructor
Point::Point(int x, int y) : X(x), Y(y) {}

// copy constructor
Point::Point(const Point & other) : X(other.X), Y(other.Y) {}

// get X
int Point::getX() const{
    return X;
}

// get Y
int Point::getY() const{
    return Y;
}

// equality tester
bool Point::operator==(const Point & other) const{
    return (X == other.X && Y == other.Y);
}

// stream insertion
ostream & operator<<(ostream &out, const Point & P){
    out << "(" << P.X << "," << P.Y << ")";
    return out;
}