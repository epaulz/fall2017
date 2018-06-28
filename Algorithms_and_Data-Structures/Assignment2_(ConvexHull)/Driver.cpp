/*
	Eric Paulz
	CPSC 2120
	Assignment 2: Graham's Scan & Convex Hulls
*/

#include "GrahamScan.h"

int main(){
    int numPoints, x, y;
    vector<Point> points;

    cout << "Enter count: ";
    cin >> numPoints;
    if(numPoints < 1)
        return 0;
    
    cout << "Enter points: ";
    for(int i = 0; i < numPoints; i++){
        cin >> x >> y;
        Point temp(x,y);
        points.push_back(temp);
    }

    moveLeftmostFirst(points);

    sortByAngleFromFirst(points);

    vector<Point> sortedPoints(convexHull(points));
    
    cout << "Hull is ";
    debugDump(sortedPoints);
    
    cout << endl;
    
    cout << "Shells are:\n";
    shellIt(points);

    return 0;
}
