/* Objective function */
max: 1 x1 + 2 x2 + 3 x3 + 4 x4 + 5 x5 + 5 y1 + 4 y2 + 3 y3  + 2 y4 + 1 y5;

/* Variable bounds */
c1: x1 >= 0;
x1 <= 1;
x2 >= 0;
x2 <= 1;
x3 >= 0;
x3 <= 1;
x4 >= 0;
x4 <= 1;
x5 >= 0;
x5 <= 1;
y1 >= 0;
y1 <= 1;
y2 >= 0;
y2 <= 1;
y3 >= 0;
y3 <= 1;
y4 >= 0;
y4 <= 1;
y5 >= 0;
y5 <= 1;

x1 + x2 + x3 + x4 + x5 <= 3;

y1 + y2 + y3 + y4 + y5 <= 3;

x1 + x2 + x3 + x4 + x5 + y1 + y2 + y3 + y4 + y5 <= 5;

int x1,x2,x3,x4,x5,y1,y2,y3,y4,y5;
