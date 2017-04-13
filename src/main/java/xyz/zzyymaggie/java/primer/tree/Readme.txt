This is my target.
input：
attr1   attr2   attr3   sales
A1  	B1  	C1  	1
A1  	B1  	C2  	1
A1  	B2  	C1  	1
A1  	B2  	C2  	1
A2  	B1  	C1  	1
A2  	B1 	C2  	1
A2  	B2  	C1  	1
A2  	B2  	C2  	1
output:
level:0 values:8 
    |--- level:1 A1:4
        |--- level:2 B1:2
            |--- level:3 C1:1
            |--- level:3 C2:1
        |--- level:2 B2:2
            |--- level:3 C1:1
            |--- level:3 C2:1   
    |--- level:1 A2:4
        |--- level:2 B1:2
            |--- level:3 C1:1
            |--- level:3 C2:1
        |--- level:2 B2:2
            |--- level:3 C1:1
            |--- level:3 C2:1
