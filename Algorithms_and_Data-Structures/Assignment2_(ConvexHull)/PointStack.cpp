/*
    Eric Paulz
    CPSC 2120
    Assignment 2: Graham's Scan & Convex Hulls
*/

#include "PointStack.h"

Point PointStack::NOTHING(0,0);

// constructor
PointStack::PointStack() : stackTop(nullptr) {}

// destructor
PointStack::~PointStack(){
    Node* temp;
    while(stackTop != nullptr){
        temp = stackTop;
        stackTop = stackTop->next;
        temp->next = nullptr;
        delete temp;
    }
}

// push item onto the stack
void PointStack::push(const Point & item){
    Node *newTop = new Node(item);
    if(stackTop == nullptr){
        stackTop = newTop;
    }
    else{
        newTop->next = stackTop;
        stackTop = newTop;
    }
}

// pop item off the stack
Point PointStack::pop(){
    if(stackTop == nullptr)
        return NOTHING;
    else{
        Node *old = stackTop;
        stackTop = stackTop->next;
        return old->data;
        delete old;
    }
}

// return data from top of stack
Point& PointStack::peek() const{
    if(stackTop == nullptr)
        return NOTHING;
    else
        return stackTop->data;
}

// return data from node under top of stack
Point& PointStack::peekUnder() const{
    if(stackTop == nullptr || stackTop->next == nullptr)
        return NOTHING;
    else
        return stackTop->next->data;
}

// test if stack is empty
bool PointStack::isEmpty() const{
    return (stackTop == nullptr);
}
